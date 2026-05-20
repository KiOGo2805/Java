package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Battery;

@Data
@NoArgsConstructor
@Schema(description = "Об'єкт передачі даних для батареї (DTO)")
public class BatteryDTO {
    @Schema(description = "Унікальний ідентифікатор інвертора", example = "1")
    private Long id;

    @Schema(description = "Виробник пристрою", example = "Victron Energy")
    private String manufacturer;

    @Schema(description = "Ємність у Ватах", example = "3000")
    private int capacityWh;

    public static BatteryDTO manualMapFromEntity(Battery battery) {
        BatteryDTO dto = new BatteryDTO();
        dto.setId(battery.getId());
        dto.setManufacturer(battery.getManufacturer());
        dto.setCapacityWh(battery.getCapacityWh());
        return dto;
    }
}
