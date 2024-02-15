package br.com.ViniciusGuedes.LaborLawsuitControl.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public record ExceptionReponse(
        LocalDateTime timestamp,
        List<String> messages
) { }
