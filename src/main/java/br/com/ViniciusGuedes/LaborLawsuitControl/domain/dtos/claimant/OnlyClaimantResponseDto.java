package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant;

public record OnlyClaimantResponseDto(
        Long claimantId,
        String claimantName,
        String rg,
        String orgaoRg,
        String cpf,
        String address,
        String stateName,
        String cityName,
        String neighborhood,
        String uf,
        String cep
) { }
