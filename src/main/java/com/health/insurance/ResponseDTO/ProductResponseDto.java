package com.health.insurance.ResponseDTO;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

	 private String productCode;
	 private String productName;
	 private LocalDate inceptionDate;
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
	   
}
