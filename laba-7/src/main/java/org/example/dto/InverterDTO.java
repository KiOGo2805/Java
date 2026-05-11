package org.example.dto;

public class InverterDTO {
    private Long id;
    private String manufacturer;
    private int maxLoadWattage;

    public InverterDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public int getMaxLoadWattage() { return maxLoadWattage; }
    public void setMaxLoadWattage(int maxLoadWattage) { this.maxLoadWattage = maxLoadWattage; }
}