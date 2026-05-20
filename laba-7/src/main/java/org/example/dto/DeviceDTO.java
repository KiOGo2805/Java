package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Об'єкт передачі даних для побутового або системного пристрою")
public class DeviceDTO {
    @Schema(description = "ID пристрою в базі даних", example = "1")
    private Long id;

    @Schema(description = "Назва або модель пристрою", example = "Холодильник Bosch")
    private String name;

    @Schema(description = "Споживана потужність пристрою у Ватах (W)", example = "250")
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