package org.example.dto;

import org.example.model.Battery;

public class BatteryDTO {
    private Long id;
    private String manufacturer;
    private int capacityWh;

    public BatteryDTO() {}

    public static BatteryDTO manualMapFromEntity(Battery battery) {
        BatteryDTO dto = new BatteryDTO();
        dto.setId(battery.getId());
        dto.setManufacturer(battery.getManufacturer());
        dto.setCapacityWh(battery.getCapacityWh());
        return dto;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public int getCapacityWh() { return capacityWh; }
    public void setCapacityWh(int capacityWh) { this.capacityWh = capacityWh; }
}