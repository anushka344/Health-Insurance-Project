package com.health.insurance.entities;



import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_collection_dtl")
public class CollectionDtl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long collectionId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long collectionDtlId;

    private Long policyId;

    private String modeOfPayment;

    private Boolean isPaid; //TRUE FALSE

    private String paidAmt;

    private String premiumAmt;

    private String createdBy;

    private String lastUpdatedBy;

    private LocalDateTime createDate;

    private LocalDateTime lastUpdatedDate;

    private LocalDateTime endDate;  //  nullable

	public Long getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(Long collectionId) {
		this.collectionId = collectionId;
	}

	public Long getCollectionDtlId() {
		return collectionDtlId;
	}

	public void setCollectionDtlId(Long collectionDtlId) {
		this.collectionDtlId = collectionDtlId;
	}

	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	public String getPaidAmt() {
		return paidAmt;
	}

	public void setPaidAmt(String paidAmt) {
		this.paidAmt = paidAmt;
	}

	public String getPremiumAmt() {
		return premiumAmt;
	}

	public void setPremiumAmt(String premiumAmt) {
		this.premiumAmt = premiumAmt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

   
}
