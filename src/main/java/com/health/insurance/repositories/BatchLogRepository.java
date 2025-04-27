package com.health.insurance.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.health.insurance.entities.BatchLog;

public interface BatchLogRepository extends JpaRepository<BatchLog, Long> {


}
