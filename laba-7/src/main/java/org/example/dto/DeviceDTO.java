package org.example.dto;

import org.example.model.DevicePassport;

public class DeviceDTO {
    private Long id;
    private String name;
    private int powerDrawWattage;
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