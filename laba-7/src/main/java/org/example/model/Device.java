package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Назва пристрою не може бути порожньою")
    private String name;

    @Column(name = "power_draw_wattage", nullable = false)
    @Min(value = 1, message = "Споживання має бути більше 0")
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