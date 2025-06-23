package com.health.insurance.services;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.health.insurance.RequestDTO.CollectPremiumRequestDto;
import com.health.insurance.RequestDTO.PolicyRequestDTO;
import com.health.insurance.ResponseDTO.CollectPremiumResponseDto;
import com.health.insurance.ResponseDTO.PolicyResponseDTO;
import com.health.insurance.entities.BatchLog;
import com.health.insurance.entities.CollectionDtl;
import com.health.insurance.entities.Party;
import com.health.insurance.entities.Policy;
import com.health.insurance.entities.Product;
import com.health.insurance.exception.CustomException;
import com.health.insurance.repositories.BatchLogRepository;
import com.health.insurance.repositories.CollectionDtlRepository;
import com.health.insurance.repositories.PartyRepository;
import com.health.insurance.repositories.PolicyRepository;
import com.health.insurance.repositories.ProductRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

@Service
public class PolicyService {
	
	 @Autowired
	    private PolicyRepository policyRepository;

	    @Autowired
	    private PartyRepository partyRepository;

	    @Autowired
	    private ProductRepository productRepository;
	    
	    @Autowired
	    private CollectionDtlRepository collectionDtlRepository;
	    
	    @Autowired
	    private BatchLogRepository batchLogRepository;

	    @Retryable(
	            value = { RuntimeException.class },
	            maxAttempts = 3,
	            backoff = @Backoff(delay = 2000)) // 2 sec delay between retries
	    public PolicyResponseDTO createQuote(PolicyRequestDTO requestDTO) {
	        Party party = partyRepository.findByPartyCode(requestDTO.getPartyCode()) .orElseThrow(() -> new CustomException("Party not found", requestDTO.getPartyCode() ));
	        Product product = productRepository.findByProductCode(requestDTO.getProductCode())
	                        .orElseThrow(() -> new CustomException("Product not found", requestDTO.getProductCode()));

	        Policy policy = new Policy();
	        policy.setPartyId(party);
	        policy.setProductId(product);
	        policy.setPartyCode(party.getPartyCode());
	        policy.setPolicyStartDate(LocalDate.parse(requestDTO.getPolicyStartDate()));
	        policy.setPolicyExpiryDate(policy.getPolicyStartDate().plusYears(1));
	        policy.setProposalNo(generateProposalNo());
	        policy.setPolicyStatus("DRAFT");

	        policyRepository.save(policy);

	        PolicyResponseDTO response = new PolicyResponseDTO();
	        response.setProposalNo(policy.getProposalNo());
	        response.setPolicyStatus("DRAFT");

	        return response;
	    }
	    
	    
	    

	    // Optional fallback method if all retries fail
	    @Recover
	    public PolicyResponseDTO recover(RuntimeException ex, PolicyRequestDTO requestDTO) {
	        System.err.println("❌ All retry attempts failed for createQuote: " + ex.getMessage());
	        throw new CustomException("All retries failed due to system error. Please try again later.", requestDTO.getPartyCode());
	    }


	    private String generateProposalNo() {
	        return "PROP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
	    }
	    
	    
		public Map<String, Object> getPremiumByProposalNo(String proposalNo) {
			
			Object[] result = policyRepository.findProposalAndPremiumByProposalNo(proposalNo);
			if (result == null) {
			    throw new CustomException("Proposal or Premium not found");
			}

			  Object[] row = (Object[]) result[0];
			  
			  String proposal = (String) row[0];
			  String premiumAmt = (String) row[1];
			  Map<String, Object> response = new HashMap<>();
			    response.put("proposalNo", proposal);
			    response.put("premiumAmt", premiumAmt);

			    return response;
		}

		@Transactional
		public CollectPremiumResponseDto collectPremium(CollectPremiumRequestDto requestDto) {
		    // Find Policy by ProposalNo
		    Policy policy = policyRepository.findByProposalNo(requestDto.getProposalNo())
		        .orElseThrow(() -> new CustomException("Proposal not found"));
		    
		    //Find premium amount of the proposal/policy
		    Map<String,Object> mp = getPremiumByProposalNo(requestDto.getProposalNo());
		    // Fetch premium amount from Map
		    String premiumAmt = (String) mp.get("premiumAmt");

		    

		    // Create new CollectionDtl
		    CollectionDtl collection = new CollectionDtl();
		    collection.setPolicyId(policy.getPolicyId());
		    collection.setModeOfPayment(requestDto.getModeOfPayment());
		    if(premiumAmt.equalsIgnoreCase(requestDto.getPaidAmt()))
		    {
		    collection.setIsPaid(true);
		    }
		    else
		    {
		    	collection.setIsPaid(false);
		    }
		    collection.setPaidAmt(requestDto.getPaidAmt());
		    collection.setPremiumAmt(premiumAmt);
		    collection.setCreatedBy("SYSTEM"); // or logged in user if available
		    collection.setLastUpdatedBy("SYSTEM");
		    collection.setCreateDate(LocalDateTime.now());
		    collection.setLastUpdatedDate(LocalDateTime.now());
		    collection.setEndDate(null);

		    // Save collection
		    collectionDtlRepository.save(collection);

		    // Prepare response
		    CollectPremiumResponseDto response = new CollectPremiumResponseDto();
		    response.setProposalNo(requestDto.getProposalNo());
		    response.setPaidAmt(requestDto.getPaidAmt());
		    if (Boolean.FALSE.equals(collection.getIsPaid()))
		    	response.setPaidStatus("Not Completed");
		    else
		    response.setPaidStatus(" Completed");

		    return response;
		}
		

	   // @Scheduled(cron = "0 0 0 * * ?") // Runs every night at 12:00 AM
      //@Scheduled(fixedDelay = 60000) // wait 60 sec after batch completed
	  //@Scheduled(fixedRate = 60000) // every 60 seconds (1 min)  even if prev batch not completed 

		// Scheduled batch to activate paid policies with timeout
	    @Scheduled(cron = "0 * * * * ?") // Runs every 1 minute (for testing)
	    @Transactional
	    public void activatePaidPoliciesBatch() {
	        BatchLog batchLog = new BatchLog();
	        batchLog.setBatchName("ActivatePaidPoliciesBatch");
	        batchLog.setStartTime(LocalDateTime.now());

	        int successCount = 0;
	        int failureCount = 0;
	        StringBuilder errorBuilder = new StringBuilder();

	        ExecutorService executorService = Executors.newSingleThreadExecutor();
	        Future<?> future = null;

	        try {
	            future = executorService.submit(() -> {
	                executeBatchLogic();
	            });

	            // Wait for execution with timeout
	            future.get(10, TimeUnit.MINUTES); // Timeout after 10 minutes
	            batchLog.setStatus("SUCCESS");
	        } catch (TimeoutException e) {
	            batchLog.setStatus("FAILED");
	            errorBuilder.append("Batch job timed out.");
	        } catch (Exception ex) {
	            batchLog.setStatus("FAILED");
	            errorBuilder.append("Error: ").append(ex.getMessage());
	        } finally {
	            batchLog.setEndTime(LocalDateTime.now());
	            batchLog.setSuccessCount(successCount);
	            batchLog.setFailureCount(failureCount);
	            batchLog.setErrorMessage(errorBuilder.toString());
	            batchLogRepository.save(batchLog);
	            executorService.shutdown();
	        }
	    }

	    private void executeBatchLogic() {
	        List<CollectionDtl> paidCollections = collectionDtlRepository.findByIsPaidTrue();

	        if (paidCollections.isEmpty()) {
	            throw new CustomException("No paid proposals found.");
	        }

	        for (CollectionDtl collectionDtl : paidCollections) {
	            try {
	                Policy policy = policyRepository.findById(collectionDtl.getPolicyId())
	                        .orElseThrow(() -> new RuntimeException("Policy not found for PolicyId: " + collectionDtl.getPolicyId()));

	                if ("DRAFT".equalsIgnoreCase(policy.getPolicyStatus()) && policy.getPolicyNo() == null) {
	                    String generatedPolicyNo = generatePolicyNo();
	                    policy.setPolicyNo(generatedPolicyNo);
	                    policy.setPolicyStatus("ACTIVE");
	                    policyRepository.save(policy);
	                }
	            } catch (Exception e) {
	                // Optional: handle individual policy processing errors
	                throw new RuntimeException("Error processing policy: " + e.getMessage());
	            }
	        }
	    }

	    private String generatePolicyNo() {
	        return "POL-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
	    }
	


}
