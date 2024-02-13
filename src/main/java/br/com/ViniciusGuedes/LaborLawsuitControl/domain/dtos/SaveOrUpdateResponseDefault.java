package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos;

import org.springframework.http.HttpStatus;

import java.util.List;

public record SaveOrUpdateResponseDefault(
        HttpStatus statusCode,
        List<String> message
) { }
