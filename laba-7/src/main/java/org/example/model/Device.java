package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "power_draw_wattage", nullable = false)
    private int powerDrawWattage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private DevicePassport passport;

    @OneToMany(mappedBy = "device")
    private List<ConsumptionLog> logs = new ArrayList<>();

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

    public DevicePassport getPassport() { return passport; }
    public void setPassport(DevicePassport passport) { this.passport = passport; }

    public List<ConsumptionLog> getLogs() { return logs; }
    public void setLogs(List<ConsumptionLog> logs) { this.logs = logs; }
}