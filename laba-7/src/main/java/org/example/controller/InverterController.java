package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.example.dto.InverterDTO;
import org.example.dto.InverterPaginationDTO;
import org.example.exception.ErrorResponse;
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
@Tag(name = "Inverter Controller", description = "REST API для управління інверторами")
public class InverterController {

    private final InverterService service;
    private final InverterMapper mapper;

    public InverterController(InverterService service, InverterMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Отримати всі інвертори", description = "Повертає повний список всіх інверторів, що є в базі даних")
    @ApiResponse(responseCode = "200", description = "Список успішно отримано")
    @GetMapping
    public List<InverterDTO> getAll() {
        return service.getAllInverters().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Знайти інвертор за ID", description = "Повертає дані інвертора, якщо він існує")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Інвертор знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = InverterDTO.class))),
            @ApiResponse(responseCode = "404", description = "Інвертор з таким ID не знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public InverterDTO getById(@PathVariable Long id) {
        Inverter inverter = service.getInverterById(id);
        return mapper.toDto(inverter);
    }

    @Operation(summary = "Створити новий інвертор", description = "Додає новий пристрій у базу даних")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Інвертор успішно створено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = InverterDTO.class))),
            @ApiResponse(responseCode = "400", description = "Помилка валідації вхідних даних (наприклад, від'ємна потужність)",
                    content = @Content)
    })
    @PostMapping
    public InverterDTO create(@Valid @RequestBody InverterDTO inverterDto) {
        Inverter inverter = mapper.toEntity(inverterDto);
        Inverter saved = service.createInverter(inverter);
        return mapper.toDto(saved);
    }

    @Operation(summary = "Оновити дані інвертора", description = "Оновлює виробника та потужність за вказаним ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Дані успішно оновлено"),
            @ApiResponse(responseCode = "404", description = "Інвертор для оновлення не знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<InverterDTO> update(@PathVariable Long id, @Valid @RequestBody InverterDTO inverterDto) {
        Inverter inverterData = mapper.toEntity(inverterDto);
        return service.updateInverter(id, inverterData)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Видалити інвертор", description = "Видаляє пристрій з бази даних за його ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Інвертор успішно видалено (No Content)"),
            @ApiResponse(responseCode = "404", description = "Інвертор не знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deleteInverter(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Отримати список інверторів сторінками (Пагінація)", description = "Повертає об'єкт з масивом інверторів та метаданими про сторінки")
    @ApiResponse(responseCode = "200", description = "Сторінку успішно завантажено",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = InverterPaginationDTO.class)))
    @GetMapping("/page")
    public InverterPaginationDTO getPage(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "5") @Min(1) @Max(100) int size
    ) {
        Page<InverterDTO> inverterPage = service.getInvertersPaginated(page, size)
                .map(mapper::toDto);

        return new InverterPaginationDTO(
                inverterPage.getContent(),
                inverterPage.getNumber(),
                inverterPage.getTotalPages(),
                inverterPage.getTotalElements(),
                inverterPage.isLast()
        );
    }
}