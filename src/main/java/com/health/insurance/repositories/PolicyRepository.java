package com.health.insurance.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.health.insurance.entities.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {

	@Query("SELECT p.proposalNo, pr.premiumAmt FROM Policy p JOIN p.productId pr WHERE p.proposalNo = :proposalNo")
	Object[] findProposalAndPremiumByProposalNo(String proposalNo);
	
	Optional<Policy> findByProposalNo(String proposalNo);
	
}