package org.example.service;

import org.example.model.Battery;
import org.example.repository.BatteryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BatteryService {

    private final BatteryRepository repository;

    public BatteryService(BatteryRepository repository) {
        this.repository = repository;
    }

    public List<Battery> getAllBatteries() {
        return repository.findAll();
    }

    public Battery getBatteryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new org.example.exception.ResourceNotFoundException("Батарею з ID " + id + " не знайдено в базі!"));
    }

    public Battery createBattery(Battery battery) {
        return repository.save(battery);
    }

    public Optional<Battery> updateBattery(Long id, Battery updatedData) {
        return repository.findById(id).map(existing -> {
            existing.setManufacturer(updatedData.getManufacturer());
            existing.setCapacityWh(updatedData.getCapacityWh());
            existing.setChemistry(updatedData.getChemistry());
            return repository.save(existing);
        });
    }

    public boolean deleteBattery(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}