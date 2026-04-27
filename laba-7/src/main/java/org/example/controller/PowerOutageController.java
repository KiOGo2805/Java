package org.example.controller;

import org.example.model.PowerOutage;
import org.example.service.PowerOutageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/outages")
public class PowerOutageController {

    private final PowerOutageService service;

    public PowerOutageController(PowerOutageService service) {
        this.service = service;
    }

    @GetMapping
    public List<PowerOutage> getAll() {
        return service.getAllOutages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PowerOutage> getById(@PathVariable Long id) {
        return service.getOutageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PowerOutage create(@RequestBody PowerOutage outage) {
        return service.createOutage(outage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PowerOutage> update(@PathVariable Long id, @RequestBody PowerOutage outage) {
        return service.updateOutage(id, outage)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deleteOutage(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}