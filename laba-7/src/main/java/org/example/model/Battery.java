package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "batteries")
public class Battery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "capacity_wh")
    private int capacityWh;

    private String chemistry;

    @ManyToMany(mappedBy = "batteries")
    private List<Inverter> inverters = new ArrayList<>();

    public Battery() {}

    public Battery(Long id, String manufacturer, int capacityWh, String chemistry) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.capacityWh = capacityWh;
        this.chemistry = chemistry;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public int getCapacityWh() { return capacityWh; }
    public void setCapacityWh(int capacityWh) { this.capacityWh = capacityWh; }

    public String getChemistry() { return chemistry; }
    public void setChemistry(String chemistry) { this.chemistry = chemistry; }

    public List<Inverter> getInverters() { return inverters; }
    public void setInverters(List<Inverter> inverters) { this.inverters = inverters; }
}