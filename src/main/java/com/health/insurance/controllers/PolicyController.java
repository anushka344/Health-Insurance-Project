package com.health.insurance.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.health.insurance.RequestDTO.CollectPremiumRequestDto;
import com.health.insurance.RequestDTO.PolicyRequestDTO;
import com.health.insurance.ResponseDTO.ApiResponse;
import com.health.insurance.ResponseDTO.CollectPremiumResponseDto;
import com.health.insurance.ResponseDTO.PolicyResponseDTO;
import com.health.insurance.Utility.ResponseUtil;
import com.health.insurance.constants.URIConstraints;
import com.health.insurance.services.PolicyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(URIConstraints.PRODUCT)
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @PostMapping("/createQuote")
    public ResponseEntity<ApiResponse<PolicyResponseDTO>> createQuote(@Valid @RequestBody PolicyRequestDTO requestDTO) {
        PolicyResponseDTO response = policyService.createQuote(requestDTO);
        System.out.println(response);
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