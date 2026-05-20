package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Лог споживання енергії конкретним пристроєм")
public class ConsumptionLogDTO {

    @Schema(description = "ID логу споживання", example = "1")
    private Long id;

    @Schema(description = "Загальна спожита енергія у Ват-годинах (Wh)", example = "1200")
    private int totalWhConsumed;

    @Schema(description = "Назва пов'язаного пристрою", example = "Холодильник Bosch")
    private String deviceName;

    public ConsumptionLogDTO() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public int getTotalWhConsumed() {
        return totalWhConsumed;
    }
    public void setTotalWhConsumed(int totalWhConsumed) {
        this.totalWhConsumed = totalWhConsumed;
    }

    public String getDeviceName() {
        return deviceName;
    }
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}