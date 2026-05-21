package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Об'єкт передачі даних про зафіксоване вимкнення електроенергії")
public class PowerOutageDTO {

    @Schema(description = "ID запису про вимкнення", example = "1")
    private Long id;

    @Schema(description = "Дата та час початку вимкнення", example = "2026-05-20T14:30:00")
    @NotBlank(message = "Час початку відключення обов'язковий")
    private String startTime;

    @Schema(description = "Дата та час відновлення електропостачання", example = "2026-05-20T18:00:00")
    private String endTime;

    public PowerOutageDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
}