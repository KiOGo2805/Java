package org.example.dto;

public class ConsumptionLogDTO {
    private Long id;
    private Long deviceId;
    private int totalWhConsumed;

    public ConsumptionLogDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getDeviceId() { return deviceId; }
    public void setDeviceId(Long deviceId) { this.deviceId = deviceId; }

    public int getTotalWhConsumed() { return totalWhConsumed; }
    public void setTotalWhConsumed(int totalWhConsumed) { this.totalWhConsumed = totalWhConsumed; }
}