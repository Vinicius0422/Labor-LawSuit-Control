package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.attorney;

public record AttorneyResponseDto(
        Long attorneyId,
        String attorneyName,
        String cpf,
        String oabNumber) { }
