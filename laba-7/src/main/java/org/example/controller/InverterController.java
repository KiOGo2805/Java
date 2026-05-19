package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.InverterDTO;
import org.example.mapper.InverterMapper;
import org.example.model.Inverter;
import org.example.service.InverterService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inverters")
public class InverterController {

    private final InverterService service;
    private final InverterMapper mapper;

    public InverterController(InverterService service, InverterMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<InverterDTO> getAll() {
        return service.getAllInverters().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InverterDTO> getById(@PathVariable Long id) {
        return service.getInverterById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public InverterDTO create(@Valid @RequestBody Inverter inverter) {
        Inverter saved = service.createInverter(inverter);
        return mapper.toDto(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InverterDTO> update(@PathVariable Long id, @Valid @RequestBody Inverter inverter) {
        return service.updateInverter(id, inverter)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deleteInverter(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/page")
    public Page<InverterDTO> getPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return service.getInvertersPaginated(page, size)
                .map(mapper::toDto);
    }
}