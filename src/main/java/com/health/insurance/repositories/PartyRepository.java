package com.health.insurance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.health.insurance.entities.Party;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
	
	List<Party> findByPartyStatus(String status);
	List<Party> findByPartyStake(String stake);
	 Optional<Party> findByPartyCode(String partyCode); 

}
