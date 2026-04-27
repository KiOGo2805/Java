package org.example.repository;

import org.example.model.Inverter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InverterRepository {
    private final Map<Long, Inverter> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Inverter> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Inverter> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public Inverter save(Inverter inverter) {
        if (inverter.getId() == null) {
            inverter.setId(idGenerator.getAndIncrement());
        }
        storage.put(inverter.getId(), inverter);
        return inverter;
    }

    public void deleteById(Long id) {
        storage.remove(id);
    }
}