package org.example.repository;

import org.example.model.Battery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class BatteryRepository {
    private final Map<Long, Battery> storage = new ConcurrentHashMap<>();

    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Battery> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Battery> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public Battery save(Battery battery) {
        if (battery.getId() == null) {
            // Якщо ID немає, видаємо новий (наприклад, 1, потім 2, 3...)
            battery.setId(idGenerator.getAndIncrement());
        }
        storage.put(battery.getId(), battery);
        return battery;
    }

    public void deleteById(Long id) {
        storage.remove(id);
    }
}