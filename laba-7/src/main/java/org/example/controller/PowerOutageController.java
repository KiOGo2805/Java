package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.PowerOutageDTO;
import org.example.mapper.PowerOutageMapper;
import org.example.model.PowerOutage;
import org.example.service.PowerOutageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/outages")
public class PowerOutageController {

    private final PowerOutageService service;
    private final PowerOutageMapper mapper;

    public PowerOutageController(PowerOutageService service, PowerOutageMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<PowerOutageDTO> getAll() {
        return service.getAllOutages().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PowerOutageDTO> getById(@PathVariable Long id) {
        return service.getOutageById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PowerOutageDTO create(@Valid @RequestBody PowerOutage outage) {
        PowerOutage saved = service.createOutage(outage);
        return mapper.toDto(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PowerOutageDTO> update(@PathVariable Long id, @Valid @RequestBody PowerOutage outage) {
        return service.updateOutage(id, outage)
                .map(mapper::toDto)
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