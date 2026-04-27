package org.example.repository;

import org.example.model.PowerOutage;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PowerOutageRepository {
    private final Map<Long, PowerOutage> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<PowerOutage> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<PowerOutage> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public PowerOutage save(PowerOutage outage) {
        if (outage.getId() == null) {
            outage.setId(idGenerator.getAndIncrement());
        }
        storage.put(outage.getId(), outage);
        return outage;
    }

    public void deleteById(Long id) {
        storage.remove(id);
    }
}