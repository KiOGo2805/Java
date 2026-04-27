package org.example.model;

public class Device {
    private Long id;
    private String name;
    private int powerDrawWattage;

    public Device() {}

    public Device(Long id, String name, int powerDrawWattage) {
        this.id = id;
        this.name = name;
        this.powerDrawWattage = powerDrawWattage;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPowerDrawWattage() { return powerDrawWattage; }
    public void setPowerDrawWattage(int powerDrawWattage) { this.powerDrawWattage = powerDrawWattage; }
}