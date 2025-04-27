package com.health.insurance.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_policy")
public class Policy {

    public Policy() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyId;

    @ManyToOne
    @JoinColumn(name = "party_id", nullable = false)
    private Party partyId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product productId;

    
    private String partyCode;

    private LocalDate policyStartDate;
    private LocalDate policyExpiryDate;

    private String proposalNo;
    private String policyNo;
    private String policyStatus;
	public Long getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}
	public Party getPartyId() {
		return partyId;
	}
	public void setPartyId(Party partyId) {
		this.partyId = partyId;
	}
	public Product getProductId() {
		return productId;
	}
	public void setProductId(Product productId) {
		this.productId = productId;
	}
	public String getPartyCode() {
		return partyCode;
	}
	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}
	public LocalDate getPolicyStartDate() {
		return policyStartDate;
	}
	public void setPolicyStartDate(LocalDate policyStartDate) {
		this.policyStartDate = policyStartDate;
	}
	public LocalDate getPolicyExpiryDate() {
		return policyExpiryDate;
	}
	public void setPolicyExpiryDate(LocalDate policyExpiryDate) {
		this.policyExpiryDate = policyExpiryDate;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}
	public Policy(Long policyId, Party partyId, Product productId, String partyCode, LocalDate policyStartDate,
			LocalDate policyExpiryDate, String proposalNo, String policyNo, String policyStatus) {
		super();
		this.policyId = policyId;
		this.partyId = partyId;
		this.productId = productId;
		this.partyCode = partyCode;
		this.policyStartDate = policyStartDate;
		this.policyExpiryDate = policyExpiryDate;
		this.proposalNo = proposalNo;
		this.policyNo = policyNo;
		this.policyStatus = policyStatus;
	}
	@Override
	public String toString() {
		return "Policy [policyId=" + policyId + ", partyId=" + partyId + ", productId=" + productId + ", partyCode="
				+ partyCode + ", policyStartDate=" + policyStartDate + ", policyExpiryDate=" + policyExpiryDate
				+ ", proposalNo=" + proposalNo + ", policyNo=" + policyNo + ", policyStatus=" + policyStatus + "]";
	}

    // Getters, setters, constructors
    
}
