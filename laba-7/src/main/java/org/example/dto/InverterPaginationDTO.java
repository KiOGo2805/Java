package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Об'єкт відповіді для посторінкового списку інверторів")
public class InverterPaginationDTO {

    @Schema(description = "Список інверторів, які потрапили на поточну сторінку")
    private List<InverterDTO> inverters;

    @Schema(description = "Номер поточної сторінки (відлік починається з 0)", example = "0")
    private int currentPage;

    @Schema(description = "Загальна кількість сторінок, доступних у базі", example = "5")
    private int totalPages;

    @Schema(description = "Загальна кількість знайдених інверторів", example = "25")
    private long totalElements;

    @Schema(description = "Чи є ця сторінка останньою", example = "false")
    private boolean isLast;
}