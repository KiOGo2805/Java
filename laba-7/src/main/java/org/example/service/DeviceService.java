package org.example.service;

import org.example.model.Device;
import org.example.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceRepository repository;

    public DeviceService(DeviceRepository repository) {
        this.repository = repository;
    }

    public List<Device> getAllDevices() {
        return repository.findAll();
    }

    public Optional<Device> getDeviceById(Long id) {
        return repository.findById(id);
    }

    public Device createDevice(Device device) {
        return repository.save(device);
    }

    public Optional<Device> updateDevice(Long id, Device updatedData) {
        return repository.findById(id).map(existing -> {
            existing.setName(updatedData.getName());
            existing.setPowerDrawWattage(updatedData.getPowerDrawWattage());
            return repository.save(existing);
        });
    }

    public boolean deleteDevice(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}