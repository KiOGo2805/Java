package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Лог споживання енергії конкретним пристроєм")
public class ConsumptionLogDTO {

    @Schema(description = "ID логу споживання", example = "1")
    private Long id;

    @Schema(description = "Загальна спожита енергія у Ват-годинах (Wh)", example = "1200")
    private int totalWhConsumed;

    @Schema(description = "Назва пов'язаного пристрою", example = "Холодильник Bosch")
    private String deviceName;
}