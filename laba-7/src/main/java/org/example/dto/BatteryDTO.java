package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.example.model.Battery;

@Schema(description = "Об'єкт передачі даних для батареї (DTO)")
public class BatteryDTO {
    @Schema(description = "Унікальний ідентифікатор інвертора", example = "1")
    private Long id;

    @Schema(description = "Виробник пристрою", example = "Victron Energy")
    @NotBlank(message = "Виробник не може бути порожнім")
    private String manufacturer;

    @Schema(description = "Ємність у Ватах", example = "3000")
    @Min(value = 1, message = "Ємність повинна бути більше 0")
    private int capacityWh;

    @Schema(description = "Тип батареї", example = "Li-ion")
    private String chemistry;

    public BatteryDTO() {}

    public static BatteryDTO manualMapFromEntity(Battery battery) {
        BatteryDTO dto = new BatteryDTO();
        dto.setId(battery.getId());
        dto.setManufacturer(battery.getManufacturer());
        dto.setCapacityWh(battery.getCapacityWh());
        dto.setChemistry(battery.getChemistry());
        return dto;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getCapacityWh() {
        return capacityWh;
    }
    public void setCapacityWh(int capacityWh) {
        this.capacityWh = capacityWh;
    }

    public String getChemistry() {
        return chemistry;
    }
    public void setChemistry(String chemistry) {
        this.chemistry = chemistry;
    }
}
