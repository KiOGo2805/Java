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
        return repository.save(log);
    }

    public Optional<ConsumptionLog> updateLog(Long id, ConsumptionLog updatedData) {
        return repository.findById(id).map(existing -> {
            existing.setDevice(updatedData.getDevice());
            existing.setTotalWhConsumed(updatedData.getTotalWhConsumed());
            return repository.save(existing);
        });
    }

    public boolean deleteLog(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}