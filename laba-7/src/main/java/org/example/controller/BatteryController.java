package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.BatteryDTO;
import org.example.mapper.BatteryMapper;
import org.example.model.Battery;
import org.example.repository.BatteryJdbcRepository;
import org.example.service.BatteryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/batteries")
public class BatteryController {

    private final BatteryService service;
    private final BatteryMapper mapper;
    private final BatteryJdbcRepository jdbcRepository;

    public BatteryController(BatteryService service, BatteryMapper mapper, BatteryJdbcRepository jdbcRepository) {
        this.service = service;
        this.mapper = mapper;
        this.jdbcRepository = jdbcRepository;
    }

    @GetMapping
    public List<BatteryDTO> getAll() {
        List<Battery> batteries = service.getAllBatteries();

        return batteries.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Battery> getById(@PathVariable Long id) {
        return service.getBatteryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Battery create(@Valid @RequestBody Battery battery) {
        return service.createBattery(battery);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Battery> update(@PathVariable Long id, @Valid @RequestBody Battery battery) {
        return service.updateBattery(id, battery)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deleteBattery(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/filter")
    public List<BatteryDTO> getBatteriesByMinCapacity(@RequestParam int min) {
        List<Battery> batteries = jdbcRepository.findBatteriesByMinCapacity(min);

        return batteries.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}