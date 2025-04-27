package com.health.insurance.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.health.insurance.entities.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	boolean existsByProductCode(String productCode);
	Optional<Product> findByProductCode(String productCode);  // Get Product object by code

}
