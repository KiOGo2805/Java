package org.example.repository;

import org.example.model.Inverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InverterRepository extends JpaRepository<Inverter, Long> {
    @Query("SELECT DISTINCT i FROM Inverter i LEFT JOIN FETCH i.batteries")
    List<Inverter> findAllWithBatteries();

    Page<Inverter> findAll(@NonNull Pageable pageable);
}