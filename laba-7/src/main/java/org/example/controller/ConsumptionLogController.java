package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.ConsumptionLogDTO;
import org.example.mapper.ConsumptionLogMapper;
import org.example.model.ConsumptionLog;
import org.example.service.ConsumptionLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/logs")
public class ConsumptionLogController {

    private final ConsumptionLogService service;
    private final ConsumptionLogMapper mapper;

    public ConsumptionLogController(ConsumptionLogService service, ConsumptionLogMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ConsumptionLogDTO> getAll() {
        return service.getAllLogs().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumptionLogDTO> getById(@PathVariable Long id) {
        return service.getLogById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ConsumptionLogDTO create(@Valid @RequestBody ConsumptionLog log) {
        ConsumptionLog saved = service.createLog(log);
        return mapper.toDto(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsumptionLogDTO> update(@PathVariable Long id, @Valid @RequestBody ConsumptionLog log) {
        return service.updateLog(id, log)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deleteLog(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}