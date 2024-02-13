package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.PersonType;

public record DefendantSomeFieldsResponseDto(
        Long defendantId,
        String defendantName,
        PersonType personType,
        String cpfCnpj
) { }
