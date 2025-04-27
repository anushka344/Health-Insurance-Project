package com.health.insurance.RequestDTO;

import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PartyRequestDTO {
	
	
	@NotBlank(message = "Party code is mandatory")
    private String partyCode;

    @NotBlank(message = "Party name is mandatory")
    private String partyName;

    @NotNull(message = "Party type is mandatory")
    private Character type;

    @NotBlank(message = "Party stake is mandatory")
    private String partyStake;

	 	private String createdBy;
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
		private LocalTime createdDate;
	 	private String lastUpdatedBy;
	 	private LocalTime lastUpdateDate;
	 	private LocalTime effectiveEndDate;

}
