package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit;

public record LawsuitSomeFieldsResponseDto(
        Long lawsuitId,
        String lawsuitNumber,
        String civilCourt,
        Double valueCase,
        Long lawsuitPhaseId,
        String phase,
        Long lawsuitStatusId,
        String status,
        Long locationId,
        String location
) { }
