package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Об'єкт передачі даних для побутового або системного пристрою")
public class DeviceDTO {
    @Schema(description = "ID пристрою в базі даних", example = "1")
    private Long id;

    @Schema(description = "Назва або модель пристрою", example = "Холодильник Bosch")
    @NotBlank(message = "Назва пристрою не може бути порожньою")
    private String name;

    @Schema(description = "Споживана потужність пристрою у Ватах (W)", example = "250")
    @Min(value = 1, message = "Споживання має бути більше 0")
    private int powerDrawWattage;

    @Schema(description = "Заводський або паспортний серійний номер", example = "SN-2026-99X")
    private String passportSerialNumber;

    public DeviceDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPowerDrawWattage() { return powerDrawWattage; }
    public void setPowerDrawWattage(int powerDrawWattage) { this.powerDrawWattage = powerDrawWattage; }

    public String getPassportSerialNumber() { return passportSerialNumber; }
    public void setPassportSerialNumber(String passportSerialNumber) { this.passportSerialNumber = passportSerialNumber; }
}