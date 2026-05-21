package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inverters")
public class Inverter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "max_load_wattage", nullable = false)
    private int maxLoadWattage;

    @ManyToMany
    @JoinTable(
            name = "inverter_battery",
            joinColumns = @JoinColumn(name = "inverter_id"),
            inverseJoinColumns = @JoinColumn(name = "battery_id")
    )
    private List<Battery> batteries = new ArrayList<>();

    public Inverter() {
    }

    public Inverter(Long id, String manufacturer, int maxPowerW) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.maxLoadWattage = maxPowerW;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public int getMaxLoadWattage() { return maxLoadWattage; }
    public void setMaxLoadWattage(int maxLoadWattage) { this.maxLoadWattage = maxLoadWattage; }

    public List<Battery> getBatteries() { return batteries; }
    public void setBatteries(List<Battery> batteries) { this.batteries = batteries; }
}