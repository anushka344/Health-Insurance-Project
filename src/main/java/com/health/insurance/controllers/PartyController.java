package com.health.insurance.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.health.insurance.RequestDTO.PartyRequestDTO;
import com.health.insurance.ResponseDTO.ApiResponse;
import com.health.insurance.ResponseDTO.PartyResponseDTO;
import com.health.insurance.Utility.ResponseUtil;
import  com.health.insurance.constants.URIConstraints;
import com.health.insurance.services.PartyService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(URIConstraints.BASE_API)
public class PartyController {
	
	@Autowired
	private PartyService partyService;
	
	//create Party
	@PostMapping(URIConstraints.INSERT_PARTY)
	public ResponseEntity<ApiResponse<PartyResponseDTO>> createParty(@Valid @RequestBody PartyRequestDTO dto)
	{
		PartyResponseDTO partyResponseDTO =partyService.createParty(dto);
		return ResponseEntity.ok(ResponseUtil.success(partyResponseDTO));
	}


	// Get all ACTIVE parties
	@GetMapping(URIConstraints.GET_ACTIVE_PARTIES)
	public ResponseEntity<ApiResponse<List<PartyResponseDTO>>> getActiveParties() {
	    List<PartyResponseDTO> parties = partyService.getActiveParties();
	    return ResponseEntity.ok(ResponseUtil.success(parties));
	}

	// Get parties by stake (e.g POLICY-HOL)
	@GetMapping(URIConstraints.GET_PARTIES_BY_STAKE)
	public ResponseEntity<ApiResponse<List<PartyResponseDTO>>> getPartiesByStake(@RequestParam String stake) {
	    List<PartyResponseDTO> parties = partyService.getPartiesByStake(stake);
	    if(parties.size() ==0) return ResponseEntity.ok(ResponseUtil.error("404","Stake parties do not exist"));
	    	return ResponseEntity.ok(ResponseUtil.success(parties));
	     
	}

}
