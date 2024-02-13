package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress;

public record ProgressRequestDto(
        String progressDate,
        String expectedDate,
        String expectedTime,
        String description,
        Long lawsuitPhaseId,
        Long lawsuitId
) { }
