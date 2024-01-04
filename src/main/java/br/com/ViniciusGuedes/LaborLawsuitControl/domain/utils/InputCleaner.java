package br.com.ViniciusGuedes.LaborLawsuitControl.domain.utils;

import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.regex.Pattern;

@Component
public class InputCleaner {

    public String cleanseNumericInput(String input){
        return input.replaceAll("[^0-9]", "");
    }

    public String removeAccents(String input) {
        if (input == null) {
            return null;
        }

        // Normalize to canonical decomposition form
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);

        // Remove diacritics (accents) using a regular expression
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");
    }

}
