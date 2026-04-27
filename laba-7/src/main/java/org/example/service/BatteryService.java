package org.example.service;

import org.example.model.Battery;
import org.example.repository.BatteryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BatteryService {

    private final BatteryRepository repository;

    // Ми не пишемо "new BatteryRepository()". Spring сам знайде створений раніше репозиторій і передасть його сюди.
    public BatteryService(BatteryRepository repository) {
        this.repository = repository;
    }

    public List<Battery> getAllBatteries() {
        return repository.findAll();
    }

    public Optional<Battery> getBatteryById(Long id) {
        return repository.findById(id);
    }

    public Battery createBattery(Battery battery) {
        if (battery.getCapacityWh() <= 0) {
            throw new IllegalArgumentException("Ємність батареї має бути більшою за 0 Wh!");
        }
        return repository.save(battery);
    }

    public Optional<Battery> updateBattery(Long id, Battery updatedData) {
        return repository.findById(id).map(existingBattery -> {
            existingBattery.setManufacturer(updatedData.getManufacturer());
            existingBattery.setCapacityWh(updatedData.getCapacityWh());
            existingBattery.setChemistry(updatedData.getChemistry());
            return repository.save(existingBattery);
        });
    }

    public boolean deleteBattery(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}