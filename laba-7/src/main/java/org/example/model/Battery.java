package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "batteries")
public class Battery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "manufacturer", nullable = false)
    @NotBlank(message = "Виробник не може бути порожнім")
    private String manufacturer;

    @Column(name = "capacity_wh")
    @Min(value = 1, message = "Ємність повинна бути більше 0")
    private int capacityWh;

    private String chemistry;
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
}