package org.example.repository;

import org.example.model.ConsumptionLog;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ConsumptionLogRepository {
    private final Map<Long, ConsumptionLog> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<ConsumptionLog> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<ConsumptionLog> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public ConsumptionLog save(ConsumptionLog log) {
        if (log.getId() == null) {
            log.setId(idGenerator.getAndIncrement());
        }
        storage.put(log.getId(), log);
        return log;
    }

    public void deleteById(Long id) {
        storage.remove(id);
    }
}