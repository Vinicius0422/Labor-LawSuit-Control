package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation;

public record AnnotationRequestDto(
        String annotationDate,
        String description,
        Long lawsuitId) {}

