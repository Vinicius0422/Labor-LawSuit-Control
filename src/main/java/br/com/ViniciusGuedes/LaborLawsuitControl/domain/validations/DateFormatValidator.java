package br.com.ViniciusGuedes.LaborLawsuitControl.domain.validations;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class DateFormatValidator {

    public DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public boolean isValidDate(String dateStr) {
        try {
            // Tenta fazer o parsing da string para LocalDate usando o formato desejado
            LocalDate.parse(dateStr, DATE_FORMATTER);
            return true; // Se o parsing for bem-sucedido, a data é válida
        } catch (DateTimeParseException e) {
            return false; // Se ocorrer uma exceção, a data não é válida
        }
    }

    public LocalDate parseLocalDate(String birthDate) {
        return LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
