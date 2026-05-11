package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "consumption_logs")
public class ConsumptionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_id", nullable = false)
    @NotNull(message = "ID пристрою обов'язкове")
    private Long deviceId;

    @Column(name = "total_wh_consumed", nullable = false)
    @Min(value = 0, message = "Споживання не може бути від'ємним")
    private int totalWhConsumed;

    public ConsumptionLog() {}

    public ConsumptionLog(Long id, Long deviceId, int totalWhConsumed) {
        this.id = id;
        this.deviceId = deviceId;
        this.totalWhConsumed = totalWhConsumed;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getDeviceId() { return deviceId; }
    public void setDeviceId(Long deviceId) { this.deviceId = deviceId; }

    public int getTotalWhConsumed() { return totalWhConsumed; }
    public void setTotalWhConsumed(int totalWhConsumed) { this.totalWhConsumed = totalWhConsumed; }
}