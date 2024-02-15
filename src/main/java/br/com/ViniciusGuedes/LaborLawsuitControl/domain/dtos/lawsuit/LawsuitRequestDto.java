package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record LawsuitRequestDto(
        @NotBlank(message = "The lawsuit number is required")
        @Size(max = 22, message = "The lawsuit number must have a maximum of 22 characters")
        String lawsuitNumber,
        @NotBlank(message = "The distribution date is required")
        String distributionDate,
        @Size(max = 10, message = "The value case must have a maximum of 10 characters")
        Double valueCase,
        Long lawsuitPhaseId,
        Long lawsuitStatusId,
        Long locationId,
        @NotBlank(message = "The claimant CPF is required")
        @Size(max = 11, min = 11, message = "The claimant CPF must have a maximum of 11 characters")
        String cpfClaimant,
        @NotBlank(message = "The defendant CPF/CNPJ is required")
        @Size(max = 14, min = 14, message = "The defendant CPF/CNPJ must have a maximum of 14 characters")
        List<String> cpfCnpjDefendants,
        @NotNull(message = "The attorney is required")
        List<Long> attorneys
) { }
