package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ProgressResponseDto {

    private Long progressId;
    private LocalDate progressDate;
    private LocalDate expectedDate;
    private LocalTime expectedTime;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProgressResponseDto() {
    }

    public ProgressResponseDto(Long progressId, LocalDate progressDate, LocalDate expectedDate, LocalTime expectedTime, String description,
                               LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.progressId = progressId;
        this.progressDate = progressDate;
        this.expectedDate = expectedDate;
        this.expectedTime = expectedTime;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return progressId;
    }

    public void setId(Long progressId) {
        this.progressId = progressId;
    }

    public LocalDate getProgressDate() {
        return progressDate;
    }

    public void setProgressDate(LocalDate progressDate) {
        this.progressDate = progressDate;
    }

    public LocalDate getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(LocalDate expectedDate) {
        this.expectedDate = expectedDate;
    }

    public LocalTime getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(LocalTime expectedTime) {
        this.expectedTime = expectedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
