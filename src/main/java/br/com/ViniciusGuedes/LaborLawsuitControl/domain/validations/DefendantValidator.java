package br.com.ViniciusGuedes.LaborLawsuitControl.domain.validations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.utils.InputCleaner;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.CityRepository;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.DefendantRepository;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefendantValidator {

    private DefendantRepository defendantRepository;
    private StateRepository stateRepository;
    private CityRepository cityRepository;
    private InputCleaner inputCleaner;

    public DefendantValidator(DefendantRepository defendantRepository, StateRepository stateRepository,
                              CityRepository cityRepository, InputCleaner inputCleaner) {
        this.defendantRepository = defendantRepository;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.inputCleaner = inputCleaner;
    }

    public List<String> validationSaveDefendantRequestDto(DefendantRequestDto defendantRequestDto){
        List errors = new ArrayList<>();
        if(defendantRequestDto.defendantName().length() < 3){
            errors.add("Enter a name with at least 3 characters!");
        }
        var personTypeUpperCase = defendantRequestDto.personType().toUpperCase();
        var personTypeFormatted = inputCleaner.removeAccents(personTypeUpperCase);
        if(!personTypeFormatted.equals("FISICA") && !personTypeFormatted.equals("JURIDICA")){
            errors.add("The person type must be FISICA or JURIDICA!");
        }
        var cpfCnpjFormatted = inputCleaner.cleanseNumericInput(defendantRequestDto.cpfCnpj());
        if(cpfCnpjFormatted.length() > 14){
            errors.add("Enter a valid CPF/CNPJ!");
        }
        if(defendantRepository.existsByCpfCnpj(cpfCnpjFormatted)){
            errors.add("CPF/CNPJ already registered for another defendant!");
        }
        if(!stateRepository.existsById(defendantRequestDto.stateId())){
            errors.add("No state found with the given id!");
        }
        if(!cityRepository.existsById(defendantRequestDto.cityId())){
            errors.add("No city found with the given id!");
        }
        var cepFormatted = inputCleaner.cleanseNumericInput(defendantRequestDto.cep());
        if(cepFormatted.length() != 8){
            errors.add("The CEP field must have 8 digits!");
        }
        return errors;
    }

    public List<String> validationUpdateDefendantRequestDto(Long id, DefendantRequestDto defendantRequestDto){
        List errors = new ArrayList<>();
        if(defendantRequestDto.defendantName().length() < 3){
            errors.add("Enter a name with at least 3 characters!");
        }
        var cpfCnpjFormatted = inputCleaner.cleanseNumericInput(defendantRequestDto.cpfCnpj());
        if(cpfCnpjFormatted.length() > 14){
            errors.add("Enter a valid CPF/CNPJ!");
        }
        boolean cpfCnpjCheck = defendantRepository.existsByCpfCnpj(cpfCnpjFormatted);
        if(cpfCnpjCheck) {
            Long cpfCnpjId = defendantRepository.findByCpfCnpjEquals(cpfCnpjFormatted).getDefendantId();
            if (cpfCnpjId != id) {
                errors.add("CPF/CNPJ already registered for another defendant!");
            }
        }
        if(!stateRepository.existsById(defendantRequestDto.stateId())){
            errors.add("No state found with the given id!");
        }
        if(!cityRepository.existsById(defendantRequestDto.cityId())){
            errors.add("No city found with the given id!");
        }
        var cepFormatted = inputCleaner.cleanseNumericInput(defendantRequestDto.cep());
        if(cepFormatted.length() != 8){
            errors.add("The CEP field must have 8 digits!");
        }
        return errors;
    }
}
