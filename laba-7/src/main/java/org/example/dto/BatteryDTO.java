package org.example.dto;

import org.example.model.Battery;

public class BatteryDTO {
    private String manufacturer;
    private int capacityWh;
    private String chemistry;

    public BatteryDTO() {}

    public static BatteryDTO manualMapFromEntity(Battery battery) {
        BatteryDTO dto = new BatteryDTO();
        dto.setManufacturer(battery.getManufacturer());
        dto.setCapacityWh(battery.getCapacityWh());
        dto.setChemistry(battery.getChemistry());
        return dto;
    }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public int getCapacityWh() { return capacityWh; }
    public void setCapacityWh(int capacityWh) { this.capacityWh = capacityWh; }

    public String getChemistry() {return chemistry;}
    public void setChemistry(String chemistry) {this.chemistry = chemistry;}
}
