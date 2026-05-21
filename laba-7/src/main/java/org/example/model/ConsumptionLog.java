package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "consumption_logs")
public class ConsumptionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_wh_consumed", nullable = false)
    private int totalWhConsumed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private Device device;

    public ConsumptionLog() {}

    public ConsumptionLog(Long id, int totalWhConsumed, Device device) {
        this.id = id;
        this.totalWhConsumed = totalWhConsumed;
        this.device = device;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getTotalWhConsumed() { return totalWhConsumed; }
    public void setTotalWhConsumed(int totalWhConsumed) { this.totalWhConsumed = totalWhConsumed; }

    public Device getDevice() { return device; }
    public void setDevice(Device device) { this.device = device; }
}