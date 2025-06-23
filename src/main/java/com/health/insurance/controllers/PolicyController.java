package com.health.insurance.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.health.insurance.RequestDTO.CollectPremiumRequestDto;
import com.health.insurance.RequestDTO.PolicyRequestDTO;
import com.health.insurance.ResponseDTO.ApiResponse;
import com.health.insurance.ResponseDTO.CollectPremiumResponseDto;
import com.health.insurance.ResponseDTO.PolicyResponseDTO;
import com.health.insurance.Utility.HashUtil;
import com.health.insurance.Utility.ResponseUtil;
import com.health.insurance.constants.URIConstraints;
import com.health.insurance.services.IdempotencyService;
import com.health.insurance.services.PolicyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(URIConstraints.PRODUCT)
public class PolicyController {
	
	@Autowired
	private ObjectMapper objectMapper;

	
	@Autowired
    private IdempotencyService idempotencyService;

    @Autowired
    private PolicyService policyService;

    @PostMapping("/createQuote")
    public ResponseEntity<ApiResponse<PolicyResponseDTO>> createQuote(
            @Valid @RequestBody PolicyRequestDTO requestDTO,
            @RequestHeader("Idempotency-Key") String key) throws JsonProcessingException {

        // Basic validation for missing or blank key
        if (key == null || key.trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(ResponseUtil.error("400", "Missing or empty Idempotency-Key header."));
        }

        // Convert request to hash using utility
        String requestHash = HashUtil.hash(objectMapper.writeValueAsString(requestDTO));

        /// Case 1: Same key, same request → return cached response
        Optional<String> cached = idempotencyService.getCachedResponse(key, requestHash);
        if (cached.isPresent()) {
            // Deserialize back to  DTO
            PolicyResponseDTO cachedResponse = objectMapper.readValue(cached.get(), PolicyResponseDTO.class);
            return ResponseEntity.ok(ResponseUtil.success(cachedResponse));
        }

        // Case 2: Same key, different request
        if (idempotencyService.isKeyUsedWithDifferentBody(key, requestHash)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ResponseUtil.error("409", "Idempotency-Key already used with a different request."));
        }

        // Case 3: First-time request
        System.out.println("Anushka " + requestDTO);
        PolicyResponseDTO response = policyService.createQuote(requestDTO);
        System.out.println(response);

        // Save the response against the key
        idempotencyService.store(key, requestHash, objectMapper.writeValueAsString(response));
        
        return ResponseEntity.ok(ResponseUtil.success(response));
    }

    
    
    @GetMapping("/getPremium")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getPremium(@RequestParam String proposalNo) {
        Map<String, Object> response = policyService.getPremiumByProposalNo(proposalNo);
        return ResponseEntity.ok(ResponseUtil.success(response));
    }
    
    @PostMapping("/collectPremium")
    public ResponseEntity<ApiResponse<CollectPremiumResponseDto>> collectPremium(@Valid @RequestBody CollectPremiumRequestDto requestDto) {
        CollectPremiumResponseDto responseDto = policyService.collectPremium(requestDto);

        ApiResponse<CollectPremiumResponseDto> apiResponse = new ApiResponse<>();
        apiResponse.setErrorCode("0");
        apiResponse.setErrorDescription("Success");
        apiResponse.setData(responseDto);

        return ResponseEntity.ok(apiResponse);
    }

}