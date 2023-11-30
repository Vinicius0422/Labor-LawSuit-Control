package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AnnotationResponseDto {

    private Long annotationId;
    private LocalDate annotationDate;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AnnotationResponseDto() {
    }

    public AnnotationResponseDto(Long annotationId, LocalDate annotationDate, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.annotationId = annotationId;
        this.annotationDate = annotationDate;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public Long getId() {
        return annotationId;
    }

    public void setId(Long annotationId) {
        this.annotationId = annotationId;
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

}
