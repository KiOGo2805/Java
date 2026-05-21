package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.dto.ConsumptionLogDTO;
import org.example.exception.ErrorResponse;
import org.example.mapper.ConsumptionLogMapper;
import org.example.model.ConsumptionLog;
import org.example.service.ConsumptionLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/logs")
@Tag(name = "Consumption Log Controller", description = "REST API для роботи з логами споживання енергії")
public class ConsumptionLogController {

    private final ConsumptionLogService service;
    private final ConsumptionLogMapper mapper;

    public ConsumptionLogController(ConsumptionLogService service, ConsumptionLogMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Отримати всі логи споживання", description = "Повертає повний список всіх записів про споживання енергії")
    @ApiResponse(responseCode = "200", description = "Список логів успішно отримано")
    @GetMapping
    public List<ConsumptionLogDTO> getAll() {
        return service.getAllLogs().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Знайти лог за ID", description = "Повертає дані конкретного запису про споживання, якщо він існує")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Лог знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsumptionLogDTO.class))),
            @ApiResponse(responseCode = "404", description = "Лог з таким ID не знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ConsumptionLogDTO getById(@PathVariable Long id) {
        ConsumptionLog log = service.getLogById(id);
        return mapper.toDto(log);
    }

    @Operation(summary = "Створити новий запис логу", description = "Додає новий запис про споживання енергії у базу даних")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запис успішно створено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsumptionLogDTO.class))),
            @ApiResponse(responseCode = "400", description = "Помилка валідації вхідних даних",
                    content = @Content)
    })
    @PostMapping
    public ConsumptionLogDTO create(@Valid @RequestBody ConsumptionLogDTO logDto) {
        ConsumptionLog log = mapper.toEntity(logDto);
        ConsumptionLog saved = service.createLog(log);
        return mapper.toDto(saved);
    }

    @Operation(summary = "Оновити дані логу", description = "Оновлює інформацію про спожиту енергію за вказаним ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Дані логу успішно оновлено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConsumptionLogDTO.class))),
            @ApiResponse(responseCode = "404", description = "Лог для оновлення не знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<ConsumptionLogDTO> update(@PathVariable Long id, @Valid @RequestBody ConsumptionLogDTO logDto) {
        ConsumptionLog logData = mapper.toEntity(logDto);
        return service.updateLog(id, logData)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Видалити лог споживання", description = "Видаляє запис з бази даних за його ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Лог успішно видалено (No Content)"),
            @ApiResponse(responseCode = "404", description = "Лог не знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deleteLog(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}