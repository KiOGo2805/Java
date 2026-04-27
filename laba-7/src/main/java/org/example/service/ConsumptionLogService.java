package org.example.service;

import org.example.model.ConsumptionLog;
import org.example.repository.ConsumptionLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumptionLogService {

    private final ConsumptionLogRepository repository;

    public ConsumptionLogService(ConsumptionLogRepository repository) {
        this.repository = repository;
    }

    public List<ConsumptionLog> getAllLogs() {
        return repository.findAll();
    }

    public Optional<ConsumptionLog> getLogById(Long id) {
        return repository.findById(id);
    }

    public ConsumptionLog createLog(ConsumptionLog log) {
        if (log.getTotalWhConsumed() <= 0) {
            throw new IllegalArgumentException("Кількість спожитої енергії повинна бути більшою за 0!");
        }
        return repository.save(log);
    }

    public Optional<ConsumptionLog> updateLog(Long id, ConsumptionLog updatedData) {
        return repository.findById(id).map(existing -> {
            existing.setDeviceId(updatedData.getDeviceId());
            existing.setTotalWhConsumed(updatedData.getTotalWhConsumed());
            return repository.save(existing);
        });
    }

    public boolean deleteLog(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}