package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.DeviceDTO;
import org.example.mapper.DeviceMapper;
import org.example.model.Device;
import org.example.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService service;
    private final DeviceMapper mapper;

    public DeviceController(DeviceService service, DeviceMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DeviceDTO> getAll() {
        return service.getAllDevices().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDTO> getById(@PathVariable Long id) {
        return service.getDeviceById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DeviceDTO create(@Valid @RequestBody Device device) {
        Device saved = service.createDevice(device);
        return mapper.toDto(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceDTO> update(@PathVariable Long id, @Valid @RequestBody Device device) {
        return service.updateDevice(id, device)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deleteDevice(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}