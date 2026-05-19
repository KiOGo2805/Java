package org.example.dto;

public class ConsumptionLogDTO {
    private Long id;
    private int totalWhConsumed;
    private String deviceName;

    public ConsumptionLogDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getTotalWhConsumed() { return totalWhConsumed; }
    public void setTotalWhConsumed(int totalWhConsumed) { this.totalWhConsumed = totalWhConsumed; }

    public String getDeviceName() { return deviceName; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }
}