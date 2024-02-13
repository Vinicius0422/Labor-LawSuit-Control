package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit;

import java.util.List;

public record LawsuitRequestDto(
        String lawsuitNumber,
        String distributionDate,
        Double valueCase,
        Long lawsuitPhaseId,
        Long lawsuitStatusId,
        Long locationId,
        String cpfClaimant,
        List<String> cpfCnpjDefendants,
        List<Long> attorneys
) { }
