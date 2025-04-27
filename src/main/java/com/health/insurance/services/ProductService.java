package com.health.insurance.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.insurance.RequestDTO.ProductRequestDto;
import com.health.insurance.ResponseDTO.ProductResponseDto;
import com.health.insurance.entities.Product;
import com.health.insurance.exception.CustomException;
import com.health.insurance.repositories.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductService {
	
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	
	@Autowired
	private ProductRepository productRepository;
	
	 public ProductResponseDto insertProduct(ProductRequestDto dto) {
	        Product product = new Product();
	  
	        if(productExists(dto.getProductCode()))
	        {
	        	//If Product Code exists throw validation
	        	logger.warn("Product Code exists in DB");
	        	throw new CustomException("Product code" + dto.getProductCode() + " exists already" );
	        }
	        
	        product.setProductCode(dto.getProductCode());
	        logger.info("Inserting product: {}", dto.getProductName());
	        product.setProductName(dto.getProductName());
	        product.setInceptionDate(dto.getInceptionDate());
	        product.setCreatedBy(dto.getCreatedBy());
	        product.setCreateDate(LocalDateTime.now());
	        product.setLastUpdatedBy(dto.getCreatedBy());
	        product.setLastUpdatedDate(LocalDateTime.now());
	        product.setPremiumAmt(dto.getPremiumAmt());
	        
	        ProductResponseDto responseDto = new ProductResponseDto();

            try {
	        productRepository.save(product);
	        logger.debug("Saving product to database...");
	        responseDto.setProductCode(product.getProductCode());
	        responseDto.setProductName(product.getProductName());
	        responseDto.setInceptionDate(product.getInceptionDate());
	        responseDto.setPremiumAmt(product.getPremiumAmt());
            }
            catch(Exception ex)
            {
            	System.out.print("Error");
            }
            
	        return responseDto;
            
            
	       
	    }
	 
	 public boolean productExists(String pdtCode)
	 {
		 boolean ans = productRepository.existsByProductCode(pdtCode);
		
		 return ans;
	 }
	 
	 
	 //GetProducts
	 public List<ProductResponseDto> getProducts(){
		 List<Product> products = productRepository.findAll();
		 List<ProductResponseDto> pdtList = new ArrayList<>();
		 for(Product p : products)
		 {
			 ProductResponseDto productResponseDto = new ProductResponseDto();
			 productResponseDto.setProductCode(p.getProductCode());
			 productResponseDto.setProductName(p.getProductName());
			 productResponseDto.setInceptionDate(p.getInceptionDate());
			 pdtList.add(productResponseDto);
		 }
		 return pdtList;
	 }
	 
	  public Optional<Product> findProductByCode(String productCode) {
	        return productRepository.findByProductCode(productCode);  // Get Product object by code
	    }
}
