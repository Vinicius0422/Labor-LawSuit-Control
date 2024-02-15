package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClaimantRequestDto(
        @NotBlank(message = "The name is required")
        @Size(max = 150, message = "The name must have a maximum of 150 characters")
        String claimantName,
        String birthDate,
        @Size(max = 50, message = "The occupation must have a maximum of 50 characters")
        String occupation,
        @NotBlank(message = "The CTPS is required")
        @Size(max = 9, message = "The CTPS must have a maximum of 9 characters")
        String ctps,
        @NotBlank(message = "The Serie CTPS is required")
        @Size(max = 9, message = "The Serie CTPS must have a maximum of 9 characters")
        String serieCtps,
        @NotBlank(message = "The RG is required")
        @Size(max = 7, min = 7,message = "The RG must have 7 characters")
        String rg,
        @NotBlank(message = "The Orgão RG is required")
        @Size(max = 10, message = "The RG must have a maximum of 10 characters")
        String orgaoRg,
        @NotBlank(message = "The CPF is required")
        @Size(max = 11, min = 11, message = "The CPF must have 11 characters")
        String cpf,
        @Size(max = 50, message = "The PIS must have a maximum of 50 characters")
        String pis,
        @NotBlank(message = "The Address is required")
        @Size(max = 255, message = "The Address must have a maximum of 255 characters")
        String address,
        @NotBlank(message = "The Neighborhood is required")
        @Size(max = 50, message = "The Neighborhood must have a maximum of 50 characters")
        String neighborhood,
        @NotBlank(message = "The CEP is required")
        @Size(max = 10, message = "The CEP must have a maximum of 10 characters")
        String cep,
        @Size(max = 50, message = "The bank must have a maximum of 50 characters")
        String bank,
        @Size(max = 10, message = "The agency must have a maximum of 10 characters")
        String agency,
        @Size(max = 10, message = "The operation must have a maximum of 10 characters")
        String operation,
        @Size(max = 20, message = "The account must have a maximum of 20 characters")
        String account,
        @NotBlank(message = "The contact is required")
        String contact,
        @Size(max = 150, message = "The email must have a maximum of 150 characters")
        String email,
        @NotNull(message = "The nationality is required")
        Long nationalityId,
        @NotNull(message = "The marital status is required")
        Long maritalStatusId,
        @NotNull(message = "The account type is required")
        Long accountTypeId,
        @NotNull(message = "The state is required")
        Long stateId,
        @NotNull(message = "The city is required")
        Long cityId
) { }
