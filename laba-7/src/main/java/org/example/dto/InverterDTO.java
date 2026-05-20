package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Об'єкт передачі даних для інвертора (DTO)")
public class InverterDTO {
    @Schema(description = "Унікальний ідентифікатор інвертора", example = "1")
    private Long id;

    @Schema(description = "Виробник пристрою", example = "Victron Energy")
    private String manufacturer;

    @Schema(description = "Максимальна потужність навантаження у Ватах", example = "3000")
    private int maxLoadWattage;
}