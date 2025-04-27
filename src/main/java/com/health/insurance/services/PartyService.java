package com.health.insurance.services;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.insurance.RequestDTO.PartyRequestDTO;
import com.health.insurance.RequestDTO.ProductRequestDto;
import com.health.insurance.ResponseDTO.PartyResponseDTO;
import com.health.insurance.ResponseDTO.ProductResponseDto;
import com.health.insurance.entities.Party;
import com.health.insurance.entities.Product;
import com.health.insurance.exception.CustomException;
import com.health.insurance.repositories.PartyRepository;
import com.health.insurance.repositories.ProductRepository;

@Service
public class PartyService {

	
	//createParty
	  private static final Logger logger = LoggerFactory.getLogger(PartyService.class);

		
		@Autowired
		private PartyRepository partyRepository;
		
		 public PartyResponseDTO createParty(PartyRequestDTO dto) {
			
			 
			 logger.info("Creating party: {}", dto.getPartyName());

		        Party party = new Party();
		        party.setPartyCode(dto.getPartyCode());
		        party.setPartyName(dto.getPartyName());
		        party.setType(dto.getType());
		        party.setPartyStake(dto.getPartyStake());
		        party.setCreatedBy(dto.getCreatedBy());
		        party.setCreatedDate(LocalTime.now());
		        party.setLastUpdatedBy(dto.getCreatedBy());
		        party.setLastUpdateDate(LocalTime.now());

		        // Auto-calculating  partyStatus as "ACTIVE" for now
		        party.setPartyStatus("ACTIVE");
		        party.setEffectiveEndDate(null);

		        // Save to DB
		        partyRepository.save(party);

		        // Build response
		        PartyResponseDTO responseDTO = new PartyResponseDTO();
		        responseDTO.setPartyCode(party.getPartyCode());
		        responseDTO.setPartyName(party.getPartyName());
		        responseDTO.setPartyStake(party.getPartyStake());
		        responseDTO.setPartyStatus(party.getPartyStatus());

		        return responseDTO;
	            
		       
		    }
		 
		 public List<PartyResponseDTO> getActiveParties() {
			    List<Party> activeParties = partyRepository.findByPartyStatus("ACTIVE");
			    return activeParties.stream().map(this::mapToDto).collect(Collectors.toList());
			}

			public List<PartyResponseDTO> getPartiesByStake(String stake) {
			    List<Party> parties = partyRepository.findByPartyStake(stake);
			    return parties.stream().map(this::mapToDto).collect(Collectors.toList());
			}

			private PartyResponseDTO mapToDto(Party party) {
			    PartyResponseDTO dto = new PartyResponseDTO();
			    dto.setPartyCode(party.getPartyCode());
			    dto.setPartyName(party.getPartyName());
			    dto.setPartyStake(party.getPartyStake());
			    dto.setPartyStatus(party.getPartyStatus());
			    return dto;
			}
			
			private  Optional<Party> findByPartyCode(String partyCode)
			{
				return partyRepository.findByPartyCode(partyCode);
			}

}
