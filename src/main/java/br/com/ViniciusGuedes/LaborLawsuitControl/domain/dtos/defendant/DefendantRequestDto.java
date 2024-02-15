package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DefendantRequestDto(
        @NotBlank(message = "The defendant name is required")
        @Size(max = 150, message = "The defendant name must have a maximum of 150 characters")
        String defendantName,
        @NotBlank(message = "The person type is required")
        String personType,
        @NotBlank(message = "The CPF/CNPJ is required")
        @Size(max = 14, min = 14, message = "The CPF/CNPJ must have a maximum of 14 characters")
        String cpfCnpj,
        String address,
        Long stateId,
        Long cityId,
        @Size(max = 50, message = "The neighborhood must have a maximum of 50 characters")
        String neighborhood,
        @Size(max = 8, message = "The CEP must have a maximum of 8 characters")
        String cep,
        String contact,
        @Size(max = 150, message = "The email must have a maximum of 150 characters")
        String email
) { }
