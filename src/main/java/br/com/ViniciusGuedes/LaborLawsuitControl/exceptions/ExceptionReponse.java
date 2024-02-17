package br.com.ViniciusGuedes.LaborLawsuitControl.exceptions;

import java.time.LocalDateTime;

public record ExceptionReponse(
        LocalDateTime timestamp,
        String messages
) { }
