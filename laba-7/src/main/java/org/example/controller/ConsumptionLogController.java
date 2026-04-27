package org.example.controller;

import org.example.model.ConsumptionLog;
import org.example.service.ConsumptionLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class ConsumptionLogController {

    private final ConsumptionLogService service;

    public ConsumptionLogController(ConsumptionLogService service) {
        this.service = service;
    }

    @GetMapping
    public List<ConsumptionLog> getAll() {
        return service.getAllLogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumptionLog> getById(@PathVariable Long id) {
        return service.getLogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ConsumptionLog create(@RequestBody ConsumptionLog log) {
        return service.createLog(log);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsumptionLog> update(@PathVariable Long id, @RequestBody ConsumptionLog log) {
        return service.updateLog(id, log)
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