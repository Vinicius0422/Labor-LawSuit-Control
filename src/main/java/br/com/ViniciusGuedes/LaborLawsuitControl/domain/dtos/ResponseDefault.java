package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos;

import org.springframework.http.HttpStatus;

public record ResponseDefault<T>(
         HttpStatus statusCode,
         String message,
         T data
) { }
