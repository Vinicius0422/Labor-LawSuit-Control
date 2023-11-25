package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AnnotationResponseDto {

    private Long id;
    private LocalDate annotationDate;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long lawsuitId;

    public AnnotationResponseDto() {
    }

    public AnnotationResponseDto(Long id, LocalDate annotationDate, String description, LocalDateTime createdAt, LocalDateTime updatedAt, Long lawsuitId) {
        this.id = id;
        this.annotationDate = annotationDate;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lawsuitId = lawsuitId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAnnotationDate() {
        return annotationDate;
    }

    public void setAnnotationDate(LocalDate annotationDate) {
        this.annotationDate = annotationDate;
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

    public Long getLawsuitId() {
        return lawsuitId;
    }

    public void setLawsuitId(Long lawsuitId) {
        this.lawsuitId = lawsuitId;
    }
}
