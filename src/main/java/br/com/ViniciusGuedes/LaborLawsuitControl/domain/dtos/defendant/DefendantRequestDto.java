package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant;

public record DefendantRequestDto(
        String defendantName,
        String personType,
        String cpfCnpj,
        String address,
        Long stateId,
        Long cityId,
        String neighborhood,
        String cep,
        String contact,
        String email
) { }
