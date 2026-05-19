package org.example.repository;

import org.example.model.DevicePassport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevicePassportRepository extends JpaRepository<DevicePassport, Long> {
}