package org.example.repository;

import org.example.model.Inverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InverterRepository extends JpaRepository<Inverter, Long> {}