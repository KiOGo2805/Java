package org.example.model;

public class Battery {
    private Long id;
    private String manufacturer;
    private int capacityWh;
    private String chemistry;

    // Обов'язковий порожній конструктор для Spring
    public Battery() {}

    public Battery(Long id, String manufacturer, int capacityWh, String chemistry) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.capacityWh = capacityWh;
        this.chemistry = chemistry;
    }

    // Getters та Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public int getCapacityWh() { return capacityWh; }
    public void setCapacityWh(int capacityWh) { this.capacityWh = capacityWh; }

    public String getChemistry() { return chemistry; }
    public void setChemistry(String chemistry) { this.chemistry = chemistry; }
}