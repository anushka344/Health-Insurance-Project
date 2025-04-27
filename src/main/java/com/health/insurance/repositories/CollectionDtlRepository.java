package com.health.insurance.repositories;
import com.health.insurance.entities.CollectionDtl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionDtlRepository extends JpaRepository<CollectionDtl, Long> {
	 List<CollectionDtl> findByIsPaidTrue(); 
	 }
