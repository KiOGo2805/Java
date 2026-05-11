package org.example.dto;

public class PowerOutageDTO {
    private Long id;
    private String startTime;
    private String endTime;

    public PowerOutageDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
}