package org.example.exception;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Універсальна структура відповіді при виникненні помилки")
public class ErrorResponse {
    @Schema(description = "Точний час виникнення помилки на сервері", example = "2026-05-20T16:40:15")
    private LocalDateTime timestamp;

    @Schema(description = "HTTP статус-код помилки", example = "404")
    private int status;

    @Schema(description = "Тип або назва HTTP помилки", example = "Not Found")
    private String error;

    @Schema(description = "Деталізоване повідомлення про причину збою", example = "Пристрій з ID 5 не знайдено")
    private String message;

    public ErrorResponse(int status, String error, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
