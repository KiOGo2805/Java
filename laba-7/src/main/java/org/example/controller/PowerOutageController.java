package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.dto.PowerOutageDTO;
import org.example.exception.ErrorResponse;
import org.example.mapper.PowerOutageMapper;
import org.example.model.PowerOutage;
import org.example.service.PowerOutageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/outages")
@Tag(name = "Power Outage Controller", description = "REST API для фіксації та управління інформацією про відключення електроенергії")
public class PowerOutageController {

    private final PowerOutageService service;
    private final PowerOutageMapper mapper;

    public PowerOutageController(PowerOutageService service, PowerOutageMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Отримати всі відключення", description = "Повертає історію всіх зафіксованих відключень електроенергії")
    @ApiResponse(responseCode = "200", description = "Список відключень успішно отримано")
    @GetMapping
    public List<PowerOutageDTO> getAll() {
        return service.getAllOutages().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Знайти відключення за ID", description = "Повертає дані конкретного відключення за його ідентифікатором")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запис знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PowerOutageDTO.class))),
            @ApiResponse(responseCode = "404", description = "Запис з таким ID не знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public PowerOutageDTO getById(@PathVariable Long id) {
        PowerOutage outage = service.getOutageById(id);
        return mapper.toDto(outage);
    }

    @Operation(summary = "Зафіксувати нове відключення", description = "Створює новий запис про відключення електроенергії")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запис успішно створено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PowerOutageDTO.class))),
            @ApiResponse(responseCode = "400", description = "Помилка валідації вхідних даних",
                    content = @Content)
    })
    @PostMapping
    public PowerOutageDTO create(@Valid @RequestBody PowerOutageDTO outageDto) {
        PowerOutage outage = mapper.toEntity(outageDto);
        PowerOutage saved = service.createOutage(outage);
        return mapper.toDto(saved);
    }

    @Operation(summary = "Оновити запис про відключення", description = "Оновлює час початку, кінця або причину відключення за вказаним ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Дані успішно оновлено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PowerOutageDTO.class))),
            @ApiResponse(responseCode = "404", description = "Запис для оновлення не знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<PowerOutageDTO> update(@PathVariable Long id, @Valid @RequestBody PowerOutageDTO outageDto) {
        PowerOutage outageData = mapper.toEntity(outageDto);
        return service.updateOutage(id, outageData)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Видалити запис про відключення", description = "Видаляє запис з бази даних")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Запис успішно видалено (No Content)"),
            @ApiResponse(responseCode = "404", description = "Запис не знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deleteOutage(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}