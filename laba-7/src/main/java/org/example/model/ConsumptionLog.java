package org.example.model;

public class ConsumptionLog {
    private Long id;
    private Long deviceId;
    private int totalWhConsumed;

    public ConsumptionLog() {}

    public ConsumptionLog(Long id, Long deviceId, int totalWhConsumed) {
        this.id = id;
        this.deviceId = deviceId;
        this.totalWhConsumed = totalWhConsumed;
    }

    // Getters та Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getDeviceId() { return deviceId; }
    public void setDeviceId(Long deviceId) { this.deviceId = deviceId; }

    public int getTotalWhConsumed() { return totalWhConsumed; }
    public void setTotalWhConsumed(int totalWhConsumed) { this.totalWhConsumed = totalWhConsumed; }
}