package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant;

public record ClaimantRequestDto(
        String claimantName,
        String birthDate,
        String occupation,
        String rg,
        String orgaoRg,
        String cpf,
        String pis,
        String address,
        String neighborhood,
        String cep,
        String bank,
        String agency,
        String operation,
        String account,
        String contact,
        String email,
        Long nationalityId,
        Long maritalStatusId,
        Long accountTypeId,
        Long stateId,
        Long cityId
) { }
