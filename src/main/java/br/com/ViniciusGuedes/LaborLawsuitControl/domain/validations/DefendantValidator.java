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

    @Autowired
    private DefendantRepository defendantRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private InputCleaner inputCleaner;

    public List<String> validationSaveDefendantRequestDto(DefendantRequestDto defendantRequestDto){
        List errors = new ArrayList<>();
        if(defendantRequestDto.getDefendantName() == null || defendantRequestDto.getDefendantName().isBlank()){
            errors.add("The name field is required!");
        }
        if(defendantRequestDto.getDefendantName().length() < 3){
            errors.add("Enter a name with at least 3 characters!");
        }
        var personTypeUpperCase = defendantRequestDto.getPersonType().toUpperCase();
        var personTypeFormatted = inputCleaner.removeAccents(personTypeUpperCase);
        if(personTypeFormatted == null || personTypeFormatted.isBlank()){
            errors.add("The Person Type is required!");
        }
        if(!personTypeFormatted.equals("FISICA") && !personTypeFormatted.equals("JURIDICA")){
            errors.add("The person type must be FISICA or JURIDICA!");
        }
        var cpfCnpjFormatted = inputCleaner.cleanseNumericInput(defendantRequestDto.getCpfCnpj());
        if(defendantRequestDto.getCpfCnpj() == null || defendantRequestDto.getCpfCnpj().isBlank()){
            errors.add("The CPF/CNPJ field is required!");
        }
        if(cpfCnpjFormatted.length() > 14){
            errors.add("Enter a valid CPF/CNPJ!");
        }
        if(defendantRepository.existsByCpfCnpj(cpfCnpjFormatted)){
            errors.add("CPF/CNPJ already registered for another defendant!");
        }
        defendantRequestDto.setCpfCnpj(cpfCnpjFormatted);
        if(defendantRequestDto.getAddress().length() > 255){
            errors.add("The Address field can have a maximum of 255 characters");
        }
        if(!errors.contains("The state field is required!") && !stateRepository.existsById(defendantRequestDto.getStateId())){
            errors.add("No state found with the given id!");
        }
        if(!errors.contains("The city field is required!") && !cityRepository.existsById(defendantRequestDto.getCityId())){
            errors.add("No city found with the given id!");
        }
        if(defendantRequestDto.getNeighborhood().length() > 50){
            errors.add("The neighborhood can have a maximum of 50 characters");
        }
        var cepFormatted = inputCleaner.cleanseNumericInput(defendantRequestDto.getCep());
        if(cepFormatted.length() > 8){
            errors.add("The CEP field must have only 8 digits!");
        }
        defendantRequestDto.setCep(cepFormatted);
        if(defendantRequestDto.getContact().length() > 255){
            errors.add("The Contact can have a maximum of 255 characters");
        }
        if(defendantRequestDto.getEmail().length() > 150){
            errors.add("The E-mail can have a maximum of 150 characters");
        }
        return errors;
    }

    public List<String> validationUpdateDefendantRequestDto(Long id, DefendantRequestDto defendantRequestDto){
        List errors = new ArrayList<>();
        if(defendantRequestDto.getDefendantName() == null || defendantRequestDto.getDefendantName().isBlank()){
            errors.add("The name field is required!");
        }
        if(defendantRequestDto.getDefendantName().length() < 3){
            errors.add("Enter a name with at least 3 characters!");
        }
        var cpfCnpjFormatted = inputCleaner.cleanseNumericInput(defendantRequestDto.getCpfCnpj());
        if(defendantRequestDto.getCpfCnpj() == null || defendantRequestDto.getCpfCnpj().isBlank()){
            errors.add("The CPF/CNPJ field is required!");
        }
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
        defendantRequestDto.setCpfCnpj(cpfCnpjFormatted);
        if(defendantRequestDto.getAddress().length() > 255){
            errors.add("The Address field can have a maximum of 255 characters");
        }
        if(!errors.contains("The state field is required!") && !stateRepository.existsById(defendantRequestDto.getStateId())){
            errors.add("No state found with the given id!");
        }
        if(!errors.contains("The city field is required!") && !cityRepository.existsById(defendantRequestDto.getCityId())){
            errors.add("No city found with the given id!");
        }
        if(defendantRequestDto.getNeighborhood().length() > 50){
            errors.add("The neighborhood can have a maximum of 50 characters");
        }
        var cepFormatted = inputCleaner.cleanseNumericInput(defendantRequestDto.getCep());
        if(cepFormatted.length() > 8){
            errors.add("The CEP field must have only 8 digits!");
        }
        defendantRequestDto.setCep(cepFormatted);
        if(defendantRequestDto.getContact().length() > 255){
            errors.add("The Contact can have a maximum of 255 characters");
        }
        if(defendantRequestDto.getEmail().length() > 150){
            errors.add("The E-mail can have a maximum of 150 characters");
        }
        if(defendantRequestDto.getCpfCnpj().isBlank()){
            errors.add("The CPF/CNPJ field is required!");
        }
        return errors;
    }
}
