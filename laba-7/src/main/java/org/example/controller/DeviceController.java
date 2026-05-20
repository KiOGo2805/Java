package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.dto.DeviceDTO;
import org.example.exception.ErrorResponse;
import org.example.mapper.DeviceMapper;
import org.example.model.Device;
import org.example.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/devices")
@Tag(name = "Device Controller", description = "REST API для управління підключеними пристроями (навантаженням)")
public class DeviceController {

    private final DeviceService service;
    private final DeviceMapper mapper;

    public DeviceController(DeviceService service, DeviceMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Отримати всі пристрої", description = "Повертає повний список всіх підключених пристроїв")
    @ApiResponse(responseCode = "200", description = "Список пристроїв успішно отримано")
    @GetMapping
    public List<DeviceDTO> getAll() {
        return service.getAllDevices().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Знайти пристрій за ID", description = "Повертає дані конкретного пристрою, якщо він існує")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пристрій знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeviceDTO.class))),
            @ApiResponse(responseCode = "404", description = "Пристрій з таким ID не знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<DeviceDTO> getById(@PathVariable Long id) {
        return service.getDeviceById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Створити новий пристрій", description = "Додає новий пристрій споживання у базу даних")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пристрій успішно створено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeviceDTO.class))),
            @ApiResponse(responseCode = "400", description = "Помилка валідації вхідних даних",
                    content = @Content)
    })
    @PostMapping
    public DeviceDTO create(@Valid @RequestBody Device device) {
        Device saved = service.createDevice(device);
        return mapper.toDto(saved);
    }

    @Operation(summary = "Оновити дані пристрою", description = "Оновлює інформацію про пристрій за вказаним ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Дані пристрою успішно оновлено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeviceDTO.class))),
            @ApiResponse(responseCode = "404", description = "Пристрій для оновлення не знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<DeviceDTO> update(@PathVariable Long id, @Valid @RequestBody Device device) {
        return service.updateDevice(id, device)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Видалити пристрій", description = "Видаляє пристрій з бази даних за його ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Пристрій успішно видалено (No Content)"),
            @ApiResponse(responseCode = "404", description = "Пристрій не знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deleteDevice(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}