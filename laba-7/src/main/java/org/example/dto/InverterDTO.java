package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Об'єкт передачі даних для інвертора (DTO)")
public class InverterDTO {
    @Schema(description = "Унікальний ідентифікатор інвертора", example = "1")
    private Long id;

    @Schema(description = "Виробник пристрою", example = "Victron Energy")
    @NotBlank(message = "Виробник не може бути порожнім")
    private String manufacturer;

    @Schema(description = "Максимальна потужність навантаження у Ватах", example = "3000")
    @Min(value = 1, message = "Потужність має бути більше 0")
    private int maxLoadWattage;

    public InverterDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public int getMaxLoadWattage() { return maxLoadWattage; }
    public void setMaxLoadWattage(int maxLoadWattage) { this.maxLoadWattage = maxLoadWattage; }
}