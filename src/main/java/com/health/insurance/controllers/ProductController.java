package com.health.insurance.controllers;

import com.health.insurance.RequestDTO.ProductRequestDto;
import  com.health.insurance.constants.URIConstraints;
import com.health.insurance.services.ProductService;
import com.health.insurance.ResponseDTO.ApiResponse;
import com.health.insurance.ResponseDTO.ProductResponseDto;
import com.health.insurance.Utility.ResponseUtil;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.health.insurance.constants.URIConstraints;

@RestController
@RequestMapping(URIConstraints.PRODUCT)
public class ProductController  {
	
	@Autowired
	private ProductService productService;
	  @PostConstruct
	    public void logURIs() {
	        System.out.println("Product URI: " + URIConstraints.PRODUCT);
	        System.out.println("Insert URI: " + URIConstraints.INSERT_PRODUCT);
	    }
	  
	//insertProduct
	
	 @PostMapping(URIConstraints.INSERT_PRODUCT)
	 public ResponseEntity<ApiResponse<ProductResponseDto>> insertProduct(@Valid @RequestBody ProductRequestDto dto) {
	     ProductResponseDto response = productService.insertProduct(dto); //  return ProductResponseDto
	     return ResponseEntity.ok(ResponseUtil.success(response));
	 }

	 //get all products
	 @GetMapping(URIConstraints.GET_PRODUCT)
	 public ResponseEntity<List<ProductResponseDto>> getAllProducts()
	 {
		 List<ProductResponseDto> productList = productService.getProducts();
		 if(productList.size() ==0)
			 return ResponseEntity.noContent().build();
		 else
			 return ResponseEntity.ok(productList);
	 }
	 
	 @GetMapping("/getPaginatedProducts")
	 public ResponseEntity<Map<String, Object>> getPaginatedProducts(
	         @RequestParam(defaultValue = "0") int page,
	         @RequestParam(defaultValue = "5") int size,
	         @RequestParam(defaultValue = "productName") String sortBy
	 ) {
	     Map<String, Object> response = productService.getPaginatedProducts(page, size, sortBy);
	     return ResponseEntity.ok(response);
	 }


}
