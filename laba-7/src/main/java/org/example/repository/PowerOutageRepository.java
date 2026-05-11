package org.example.repository;

import org.example.model.PowerOutage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerOutageRepository extends JpaRepository<PowerOutage, Long> {
}