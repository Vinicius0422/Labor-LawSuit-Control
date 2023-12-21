package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.SaveOrUpdateResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Defendant;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.DefendantService;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DefendantServiceImpl implements DefendantService {

    @Autowired
    private DefendantRepository defendantRepository;
    @Autowired
    private ClaimantRepository claimantRepository;
    @Autowired
    private NationalityRepository nationalityRepository;
    @Autowired
    private MaritalStatusRepository maritalStatusRepository;
    @Autowired
    private AccountTypeRepository accountTypeRepository;
    @Autowired
    private AnnotationRepository annotationRepository;
    @Autowired
    private AttorneyRepository attorneyRepository;
    @Autowired
    private LawsuitRepository lawsuitRepository;
    @Autowired
    private ProgressRepository progressRepository;

    @Override
    public ResponseDefault getAllDefendants() {
        var defendants = defendantRepository.findAllDefendants();
        return new ResponseDefault(HttpStatus.OK, "Search carried out!", defendants);
    }

    @Override
    public ResponseDefault getDefendantByName(String defendantName) {
        if(defendantName.length() < 3){
            return new ResponseDefault(HttpStatus.BAD_REQUEST,"Enter a name with at least 3 characters!", null);
        }
        var defendantsDto = defendantRepository.findDefendantByNameContains(defendantName);
        if(defendantsDto.isEmpty()){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No records found for this name!", null);
        }
        for (int i = 0; i < defendantsDto.size(); i++) {
            var lawsuitsDto = lawsuitRepository.findLawsuitByDefendatId(defendantsDto.get(i).getDefendantId());
            defendantsDto.get(i).setLawsuits(lawsuitsDto);
        }
        return new ResponseDefault(HttpStatus.OK, "Search carried out!", defendantsDto);
    }

    @Override
    public ResponseDefault getDefendantById(Long defendantId) {
        var defendant = defendantRepository.findDefendantById(defendantId).orElse(null);
        if(defendant == null){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No records found for this ID!", null);
        }
        var lawsuitsDto = lawsuitRepository.findLawsuitByDefendatId(defendantId);
        if(lawsuitsDto.isEmpty()){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No results found!", null);
        }
        defendant.setLawsuits(lawsuitsDto);
        return new ResponseDefault(HttpStatus.OK, "Search carried out!", defendant);
    }

    @Override
    public ResponseDefault getDefendantByCpfOrCnpj(String cpfCnpj) {
        var defendant = defendantRepository.findDefendantByCpfOrCnpj(cleanseNumericInput(cpfCnpj)).orElse(null);
        if(defendant == null){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No records found for this CPF/CNPJ!", null);
        }
        var lawsuitsDto = lawsuitRepository.findLawsuitByDefendatId(defendant.getDefendantId());
        if(lawsuitsDto.isEmpty()){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No results found!", null);
        }
        defendant.setLawsuits(lawsuitsDto);
        return new ResponseDefault(HttpStatus.OK, "Search carried out!", defendant);
    }

    @Override
    public SaveOrUpdateResponseDefault saveDefendant(DefendantRequestDto defendantRequestDto) {
        var validation = saveDefendantValidation(defendantRequestDto);
        if(!validation.isEmpty()){
            return new SaveOrUpdateResponseDefault(HttpStatus.CONFLICT, validation);
        }
        Defendant defendant = defendantDtoToEntity(defendantRequestDto);
        defendant.setCreatedAt(LocalDateTime.now());
        defendantRepository.save(defendant);
        return new SaveOrUpdateResponseDefault(HttpStatus.CREATED, Collections.singletonList("Successfully created!"));
    }

    @Override
    public SaveOrUpdateResponseDefault updateDefendant(Long id, DefendantRequestDto defendantRequestDto) {
        var defendantToUpdate = defendantRepository.findById(id).orElse(null);
        if(defendantToUpdate == null){
            return new SaveOrUpdateResponseDefault(HttpStatus.NOT_FOUND, Collections.singletonList("No records found for this ID!"));
        }
        var validation = updateDefendantValidation(id, defendantRequestDto);
        if(!validation.isEmpty()) {
            return  new SaveOrUpdateResponseDefault(HttpStatus.CONFLICT, validation);
        }
        Defendant existingDefendant = defendantToUpdate;
        updateDefendantFields(existingDefendant, defendantRequestDto);
        existingDefendant.setUpdatedAt(LocalDateTime.now());
        defendantRepository.save(existingDefendant);
        return new SaveOrUpdateResponseDefault(HttpStatus.OK, Collections.singletonList("Updated successfully!"));
    }

    public List<String> saveDefendantValidation(DefendantRequestDto defendantRequestDto){
        List errors = new ArrayList<>();
        var cpfCnpjFormatted = cleanseNumericInput(defendantRequestDto.getCpfCnpj());
        var cepFormatted = cleanseNumericInput(defendantRequestDto.getCep());
        if(cpfCnpjFormatted.length() > 14){
            errors.add("Enter a valid CPF/CNPJ!");
        }
        if(defendantRequestDto.getUf().length() > 2){
            errors.add("The UF field must have only 2 digits!");
        }
        if(cepFormatted.length() > 8){
            errors.add("The CEP field must have only 8 digits!");
        }
        if(defendantRequestDto.getDefendantName().isBlank()){
            errors.add("The name field is required!");
        }
        if(defendantRequestDto.getDefendantName().length() < 3){
            errors.add("Enter a name with at least 3 characters!");
        }
        if(defendantRequestDto.getCpfCnpj().isBlank()){
            errors.add("The CPF/CNPJ field is required!");
        }
        if(defendantRepository.existsByCpfCnpj(cpfCnpjFormatted)){
            errors.add("CPF/CNPJ already registered for another defendant!");
        }
        return errors;
    }

    public List<String> updateDefendantValidation(Long id, DefendantRequestDto defendantRequestDto){
        List errors = new ArrayList<>();
        var cpfCnpjFormatted = cleanseNumericInput(defendantRequestDto.getCpfCnpj());
        var cepFormatted = cleanseNumericInput(defendantRequestDto.getCep());
        if(cpfCnpjFormatted.length() > 14){
            errors.add("Enter a valid CPF/CNPJ!");
        }
        if(defendantRequestDto.getUf().length() > 2){
            errors.add("The UF field must have only 2 digits!");
        }
        if(cepFormatted.length() > 8){
            errors.add("The CEP field must have only 8 digits!");
        }
        if(defendantRequestDto.getDefendantName().isBlank()){
            errors.add("The name field is required!");
        }
        if(defendantRequestDto.getDefendantName().length() < 3){
            errors.add("Enter a name with at least 3 characters!");
        }
        if(defendantRequestDto.getCpfCnpj().isBlank()){
            errors.add("The CPF/CNPJ field is required!");
        }
        boolean cpfCnpjCheck = defendantRepository.existsByCpfCnpj(cpfCnpjFormatted);
        if(cpfCnpjCheck) {
            Long cpfCnpjId = defendantRepository.findByCpfCnpjEquals(cpfCnpjFormatted).getDefendantId();
            if (cpfCnpjId != id) {
                errors.add("CPF/CNPJ already registered for another defendant!");
            }
        }
        return errors;
    }

    public String cleanseNumericInput(String input){
        return input.replaceAll("[^0-9]", "");
    }

    public void updateDefendantFields(Defendant existingDefendant, DefendantRequestDto defendantRequestDto){
        existingDefendant.setDefendantName(defendantRequestDto.getDefendantName());
        existingDefendant.setPersonType(defendantRequestDto.getPersonType());
        existingDefendant.setCpfCnpj(cleanseNumericInput(defendantRequestDto.getCpfCnpj()));
        existingDefendant.setAddress(defendantRequestDto.getAddress());
        existingDefendant.setCity(defendantRequestDto.getCity());
        existingDefendant.setNeighborhood(defendantRequestDto.getNeighborhood());
        existingDefendant.setUf(defendantRequestDto.getUf());
        existingDefendant.setCep(cleanseNumericInput(defendantRequestDto.getCep()));
        existingDefendant.setEmail(defendantRequestDto.getEmail());
    }

    public Defendant defendantDtoToEntity(DefendantRequestDto defendantRequestDto){
        Defendant defendant = new Defendant();
        defendant.setDefendantName(defendantRequestDto.getDefendantName());
        defendant.setPersonType(defendantRequestDto.getPersonType());
        defendant.setCpfCnpj(cleanseNumericInput(defendantRequestDto.getCpfCnpj()));
        defendant.setAddress(defendantRequestDto.getAddress());
        defendant.setCity(defendantRequestDto.getCity());
        defendant.setNeighborhood(defendantRequestDto.getNeighborhood());
        defendant.setUf(defendantRequestDto.getUf());
        defendant.setCep(cleanseNumericInput(defendantRequestDto.getCep()));
        defendant.setContact(defendantRequestDto.getContact());
        defendant.setEmail(defendantRequestDto.getEmail());

        return defendant;
    }
}
