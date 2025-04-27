package com.health.insurance.entities;

import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="t_party")
public class Party {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long partyId;
	 	private String partyCode;
	 	private String partyName;
	 	private Character type;
	 	private String partyStake;
	 	private String partyStatus;
	 	private String createdBy;
	 	private LocalTime createdDate;
	 	private String lastUpdatedBy;
	 	private LocalTime lastUpdateDate;
	 	private LocalTime effectiveEndDate;
		public Long getPartyId() {
			return partyId;
		}
		public void setPartyId(Long partyId) {
			this.partyId = partyId;
		}
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
		public Character getType() {
			return type;
		}
		public void setType(Character type) {
			this.type = type;
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
		public String getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}
		public LocalTime getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(LocalTime createdDate) {
			this.createdDate = createdDate;
		}
		public String getLastUpdatedBy() {
			return lastUpdatedBy;
		}
		public void setLastUpdatedBy(String lastUpdatedBy) {
			this.lastUpdatedBy = lastUpdatedBy;
		}
		public LocalTime getLastUpdateDate() {
			return lastUpdateDate;
		}
		public void setLastUpdateDate(LocalTime lastUpdateDate) {
			this.lastUpdateDate = lastUpdateDate;
		}
		public LocalTime getEffectiveEndDate() {
			return effectiveEndDate;
		}
		public void setEffectiveEndDate(LocalTime effectiveEndDate) {
			this.effectiveEndDate = effectiveEndDate;
		}
	
	 

}
