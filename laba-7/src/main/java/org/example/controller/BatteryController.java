package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.dto.BatteryDTO;
import org.example.exception.ErrorResponse;
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
@Tag(name = "Battery Controller", description = "REST API для роботи з акумуляторними батареями")
public class BatteryController {

    private final BatteryService service;
    private final BatteryMapper mapper;
    private final BatteryJdbcRepository jdbcRepository;

    public BatteryController(BatteryService service, BatteryMapper mapper, BatteryJdbcRepository jdbcRepository) {
        this.service = service;
        this.mapper = mapper;
        this.jdbcRepository = jdbcRepository;
    }

    @Operation(summary = "Отримати всі батареї", description = "Повертає повний список всіх акумуляторів з бази даних")
    @ApiResponse(responseCode = "200", description = "Список успішно отримано")
    @GetMapping
    public List<BatteryDTO> getAll() {
        List<Battery> batteries = service.getAllBatteries();

        return batteries.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Знайти батарею за ID", description = "Повертає дані акумуляторної батареї, якщо вона існує")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Батарею знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BatteryDTO.class))),
            @ApiResponse(responseCode = "404", description = "Батарею з таким ID не знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public BatteryDTO getById(@PathVariable Long id) {
        Battery battery = service.getBatteryById(id);
        return mapper.toDto(battery);
    }

    @Operation(summary = "Створити нову батарею", description = "Додає новий акумулятор у базу даних")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Батарею успішно створено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BatteryDTO.class))),
            @ApiResponse(responseCode = "400", description = "Помилка валідації вхідних даних (наприклад, від'ємна ємність)",
                    content = @Content)
    })
    @PostMapping
    public Battery create(@Valid @RequestBody BatteryDTO batteryDto) {
        Battery battery = mapper.toEntity(batteryDto);
        return service.createBattery(battery);
    }

    @Operation(summary = "Оновити дані батареї", description = "Оновлює виробника, ємність та хімію за вказаним ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Дані успішно оновлено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BatteryDTO.class))),
            @ApiResponse(responseCode = "404", description = "Батарею для оновлення не знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Battery> update(@PathVariable Long id, @Valid @RequestBody BatteryDTO batteryDto) {
        Battery batteryData = mapper.toEntity(batteryDto);
        return service.updateBattery(id, batteryData)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Видалити батарею", description = "Видаляє пристрій з бази даних за його ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Батарею успішно видалено (No Content)"),
            @ApiResponse(responseCode = "404", description = "Батарею не знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deleteBattery(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Фільтрація батарей за ємністю", description = "Повертає список батарей, ємність яких більша або дорівнює вказаному значенню")
    @ApiResponse(responseCode = "200", description = "Відфільтрований список успішно отримано")
    @GetMapping("/filter")
    public List<BatteryDTO> getBatteriesByMinCapacity(@RequestParam int min) {
        List<Battery> batteries = jdbcRepository.findBatteriesByMinCapacity(min);

        return batteries.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}