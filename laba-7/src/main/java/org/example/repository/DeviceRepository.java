package org.example.repository;

import org.example.model.Device;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class DeviceRepository {
    private final Map<Long, Device> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Device> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Device> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public Device save(Device device) {
        if (device.getId() == null) {
            device.setId(idGenerator.getAndIncrement());
        }
        storage.put(device.getId(), device);
        return device;
    }

    public void deleteById(Long id) {
        storage.remove(id);
    }
}