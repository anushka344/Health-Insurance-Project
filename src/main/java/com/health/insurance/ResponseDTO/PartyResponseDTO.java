package com.health.insurance.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PartyResponseDTO {
	private String partyCode;
 	private String partyName;
 	private String partyStake;
 	private String partyStatus;
	public String getPartyCode() {
		return partyCode;
	}
	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getPartyStake() {
		return partyStake;
	}
	public void setPartyStake(String partyStake) {
		this.partyStake = partyStake;
	}
	public String getPartyStatus() {
		return partyStatus;
	}
	public void setPartyStatus(String partyStatus) {
		this.partyStatus = partyStatus;
	}
 	
 	

}
