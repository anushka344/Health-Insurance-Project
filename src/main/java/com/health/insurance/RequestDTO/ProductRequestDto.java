package com.health.insurance.RequestDTO;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
	
	 @NotBlank(message = "Product code cannot be blank")
	    private String productCode;

	    @NotBlank(message = "Product name cannot be blank")
	    private String productName;

	    @NotNull(message = "Inception date is required")
	    private LocalDate inceptionDate;

	    @NotBlank(message = "Created by cannot be blank")
	    private String createdBy;
	    
	    @NotBlank(message = "Premium amount for product cannot be blank")
	    private String premiumAmt;
	
		public String getPremiumAmt() {
			return premiumAmt;
		}
		public void setPremiumAmt(String premiumAmt) {
			this.premiumAmt = premiumAmt;
		}
		public String getProductCode() {
			return productCode;
		}
		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public LocalDate getInceptionDate() {
			return inceptionDate;
		}
		public void setInceptionDate(LocalDate inceptionDate) {
			this.inceptionDate = inceptionDate;
		}
		public String getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}

}
