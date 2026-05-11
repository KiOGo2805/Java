package org.example.repository;

import org.example.model.ConsumptionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumptionLogRepository extends JpaRepository<ConsumptionLog, Long> {
}