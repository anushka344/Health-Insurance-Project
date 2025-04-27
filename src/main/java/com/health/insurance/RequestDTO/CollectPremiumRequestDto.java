package com.health.insurance.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CollectPremiumRequestDto {
	
	@NotBlank(message = "Proposal number is mandatory")
	  private String proposalNo;
	@NotBlank(message = "Payment Mode is mandatory")
	  private String modeOfPayment;
	@NotBlank(message = "Paid Amount is mandatory")
	  private String paidAmt;
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	public String getPaidAmt() {
		return paidAmt;
	}
	public void setPaidAmt(String paidAmt) {
		this.paidAmt = paidAmt;
	}


}
