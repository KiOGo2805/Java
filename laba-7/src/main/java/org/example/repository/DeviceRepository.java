package org.example.repository;

import org.example.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    @Query("SELECT DISTINCT d FROM Device d LEFT JOIN FETCH d.logs")
    List<Device> findAllWithLogs();

    @Query("SELECT d FROM Device d LEFT JOIN FETCH d.passport WHERE d.id = :id")
    Optional<Device> findByIdWithPassport(Long id);
}