package com.health.insurance.repositories;

import java.util.Optional;
import com.health.insurance.entities.IdempotencyRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IdempotencyRepository extends JpaRepository<IdempotencyRecord, String> {
    Optional<IdempotencyRecord> findByIdAndRequestHash(String id, String requestHash);
}
