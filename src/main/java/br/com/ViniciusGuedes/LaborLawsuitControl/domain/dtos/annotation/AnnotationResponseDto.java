package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AnnotationResponseDto(
         Long annotationId,
         LocalDate annotationDate,
         String description,
         LocalDateTime createdAt,
         LocalDateTime updatedAt
) { }
