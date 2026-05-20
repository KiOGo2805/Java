package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Об'єкт відповіді для посторінкового списку інверторів")
public class InverterPaginationDTO {

    @Schema(description = "Список інверторів, які потрапили на поточну сторінку")
    private List<InverterDTO> inverters;

    @Schema(description = "Номер поточної сторінки (відлік починається з 0)", example = "0")
    private int currentPage;

    @Schema(description = "Загальна кількість сторінок, доступних у базі", example = "5")
    private int totalPages;

    @Schema(description = "Загальна кількість знайдених інверторів", example = "25")
    private long totalElements;

    @Schema(description = "Чи є ця сторінка останньою", example = "false")
    private boolean isLast;

    public InverterPaginationDTO() {}

    public InverterPaginationDTO(List<InverterDTO> inverters, int currentPage, int totalPages, long totalElements, boolean isLast) {
        this.inverters = inverters;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.isLast = isLast;
    }

    public List<InverterDTO> getInverters() {
        return inverters;
    }
    public void setInverters(List<InverterDTO> inverters) {
        this.inverters = inverters;
    }

    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }
    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isLast() {
        return isLast;
    }
    public void setLast(boolean isLast) {
        this.isLast = isLast;
    }
}