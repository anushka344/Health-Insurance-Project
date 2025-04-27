package com.health.insurance.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor
public class PolicyRequestDTO {
    private String productCode;
    private String partyCode;
    private String policyStartDate; // in format yyyy-MM-dd
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getPartyCode() {
		return partyCode;
	}
	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}
	public String getPolicyStartDate() {
		return policyStartDate;
	}
	public void setPolicyStartDate(String policyStartDate) {
		this.policyStartDate = policyStartDate;
	}
	@Override
	public String toString() {
		return "PolicyRequestDTO [productCode=" + productCode + ", partyCode=" + partyCode + ", policyStartDate="
				+ policyStartDate + "]";
	}

    // Getters and Setters
    
}
