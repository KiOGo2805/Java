package org.example.service;

import org.example.model.Inverter;
import org.example.repository.InverterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InverterService {

    private final InverterRepository repository;

    public InverterService(InverterRepository repository) {
        this.repository = repository;
    }

    public List<Inverter> getAllInverters() {
        return repository.findAll();
    }

    public Optional<Inverter> getInverterById(Long id) {
        return repository.findById(id);
    }

    public Inverter createInverter(Inverter inverter) {
        if (inverter.getMaxLoadWattage() <= 0) {
            throw new IllegalArgumentException("Потужність інвертора має бути більшою за 0 Вт!");
        }
        return repository.save(inverter);
    }

    public Optional<Inverter> updateInverter(Long id, Inverter updatedData) {
        return repository.findById(id).map(existing -> {
            existing.setBrand(updatedData.getBrand());
            existing.setMaxLoadWattage(updatedData.getMaxLoadWattage());
            return repository.save(existing);
        });
    }

    public boolean deleteInverter(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}