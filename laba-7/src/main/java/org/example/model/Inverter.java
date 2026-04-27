package org.example.model;

public class Inverter {
    private Long id;
    private String brand;
    private int maxLoadWattage;

    public Inverter() {}

    public Inverter(Long id, String brand, int maxLoadWattage) {
        this.id = id;
        this.brand = brand;
        this.maxLoadWattage = maxLoadWattage;
    }

    // Getters та Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public int getMaxLoadWattage() { return maxLoadWattage; }
    public void setMaxLoadWattage(int maxLoadWattage) { this.maxLoadWattage = maxLoadWattage; }
}