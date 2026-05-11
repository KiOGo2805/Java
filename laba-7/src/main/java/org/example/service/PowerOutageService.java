package org.example.service;

import org.example.model.PowerOutage;
import org.example.repository.PowerOutageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PowerOutageService {

    private final PowerOutageRepository repository;

    public PowerOutageService(PowerOutageRepository repository) {
        this.repository = repository;
    }

    public List<PowerOutage> getAllOutages() {
        return repository.findAll();
    }

    public Optional<PowerOutage> getOutageById(Long id) {
        return repository.findById(id);
    }

    public PowerOutage createOutage(PowerOutage outage) {
        return repository.save(outage);
    }

    public Optional<PowerOutage> updateOutage(Long id, PowerOutage updatedData) {
        return repository.findById(id).map(existing -> {
            existing.setStartTime(updatedData.getStartTime());
            existing.setEndTime(updatedData.getEndTime());
            return repository.save(existing);
        });
    }

    public boolean deleteOutage(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}