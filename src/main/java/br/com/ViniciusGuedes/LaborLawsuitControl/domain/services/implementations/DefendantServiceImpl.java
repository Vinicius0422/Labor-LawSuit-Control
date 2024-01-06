package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.SaveOrUpdateResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.City;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Defendant;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.PersonType;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.State;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.DefendantService;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.utils.InputCleaner;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.validations.ClaimantValidator;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.validations.DefendantValidator;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private DefendantValidator defendantValidator;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private InputCleaner inputCleaner;

    @Override
    @Transactional(readOnly = true)
    public ResponseDefault getAllDefendants() {
        var defendants = defendantRepository.findAllDefendants();
        if(defendants.isEmpty()){
            return new ResponseDefault(HttpStatus.OK, "No records found!", defendants);
        }
        return new ResponseDefault<>(HttpStatus.OK, "Search carried out!", defendants);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDefault getDefendantById(Long defendantId) {
        var defendant = defendantRepository.findDefendantById(defendantId).orElse(null);
        if(defendant == null){
            return new ResponseDefault(HttpStatus.OK, "No records found!", null);
        }
        var lawsuitsDto = lawsuitRepository.findLawsuitByDefendatId(defendantId);
        defendant.setLawsuits(lawsuitsDto);
        return new ResponseDefault(HttpStatus.OK, "Search carried out!", defendant);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDefault getDefendantByCpfOrCnpj(String cpfCnpj) {
        String cpfCnpjFormatted = inputCleaner.cleanseNumericInput(cpfCnpj);
        if(cpfCnpjFormatted.length() != 11 && cpfCnpjFormatted.length() != 14){
            return new ResponseDefault(HttpStatus.BAD_REQUEST, "The CPF/CNPJ provided does not meet the requirements");
        }
        var defendant = defendantRepository.findDefendantByCpfOrCnpj(cpfCnpjFormatted).orElse(null);
        if(defendant == null){
            return new ResponseDefault(HttpStatus.OK, "No records found!", null);
        }
        var lawsuitsDto = lawsuitRepository.findLawsuitByDefendatId(defendant.getDefendantId());
        defendant.setLawsuits(lawsuitsDto);
        return new ResponseDefault(HttpStatus.OK, "Search carried out!", defendant);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDefault getDefendantByName(String defendantName) {
        if(defendantName.length() < 3){
            return new ResponseDefault(HttpStatus.BAD_REQUEST,"Enter a name with at least 3 characters!", null);
        }
        var defendants = defendantRepository.findDefendantByNameContains(defendantName);
        if(defendants.isEmpty()){
            return new ResponseDefault(HttpStatus.OK, "No records found!", defendants);
        }
        for (int i = 0; i < defendants.size(); i++) {
            var lawsuitsDto = lawsuitRepository.findLawsuitByDefendatId(defendants.get(i).getDefendantId());
            defendants.get(i).setLawsuits(lawsuitsDto);
        }
        return new ResponseDefault(HttpStatus.OK, "Search carried out!", defendants);
    }

    @Override
    @Transactional
    public SaveOrUpdateResponseDefault saveDefendant(DefendantRequestDto defendantRequestDto) {
        var validation = defendantValidator.validationSaveDefendantRequestDto(defendantRequestDto);
        if(!validation.isEmpty()){
            return new SaveOrUpdateResponseDefault(HttpStatus.CONFLICT, validation);
        }
        Defendant defendant = defendantDtoToEntity(defendantRequestDto);
        defendant.setCreatedAt(LocalDateTime.now());
        defendantRepository.save(defendant);
        return new SaveOrUpdateResponseDefault(HttpStatus.CREATED, Collections.singletonList("Successfully created!"));
    }

    @Override
    @Transactional
    public SaveOrUpdateResponseDefault updateDefendant(Long id, DefendantRequestDto defendantRequestDto) {
        var defendantToUpdate = defendantRepository.findById(id).orElse(null);
        if(defendantToUpdate == null){
            return new SaveOrUpdateResponseDefault(HttpStatus.CONFLICT, Collections.singletonList("No records found for this ID!"));
        }
        var validation = defendantValidator.validationUpdateDefendantRequestDto(id, defendantRequestDto);
        if(!validation.isEmpty()) {
            return  new SaveOrUpdateResponseDefault(HttpStatus.CONFLICT, validation);
        }
        Defendant existingDefendant = defendantToUpdate;
        updateDefendantFields(existingDefendant, defendantRequestDto);
        existingDefendant.setUpdatedAt(LocalDateTime.now());
        defendantRepository.save(existingDefendant);
        return new SaveOrUpdateResponseDefault(HttpStatus.OK, Collections.singletonList("Updated successfully!"));
    }

    public void updateDefendantFields(Defendant existingDefendant, DefendantRequestDto defendantRequestDto){
        State state = stateRepository.findById(defendantRequestDto.getStateId()).orElse(null);
        City city = cityRepository.findById(defendantRequestDto.getCityId()).orElse(null);

        existingDefendant.setDefendantName(inputCleaner.removeAccents(defendantRequestDto.getDefendantName()));
        PersonType personType;
        if(defendantRequestDto.getPersonType() == "FISICA"){
            personType = PersonType.FISICA;
        } else {
            personType = PersonType.JURIDICA;
        }
        existingDefendant.setPersonType(personType);
        existingDefendant.setCpfCnpj(defendantRequestDto.getCpfCnpj());
        existingDefendant.setAddress(inputCleaner.removeAccents(defendantRequestDto.getAddress()));
        existingDefendant.setState(state);
        existingDefendant.setCity(city);
        existingDefendant.setNeighborhood(inputCleaner.removeAccents(defendantRequestDto.getNeighborhood()));
        existingDefendant.setUf(city.getUf());
        existingDefendant.setCep(defendantRequestDto.getCep());
        existingDefendant.setContact(inputCleaner.removeAccents(defendantRequestDto.getContact()));
        existingDefendant.setEmail(inputCleaner.removeAccents(defendantRequestDto.getEmail()));
    }

    public Defendant defendantDtoToEntity(DefendantRequestDto defendantRequestDto){
        Defendant defendant = new Defendant();

        State state = stateRepository.findById(defendantRequestDto.getStateId()).orElse(null);
        City city = cityRepository.findById(defendantRequestDto.getCityId()).orElse(null);

        defendant.setDefendantName(inputCleaner.removeAccents(defendantRequestDto.getDefendantName()));
        PersonType personType;
        if(defendantRequestDto.getPersonType() == "FISICA"){
            personType = PersonType.FISICA;
        } else {
            personType = PersonType.JURIDICA;
        }
        defendant.setPersonType(personType);
        defendant.setCpfCnpj(defendantRequestDto.getCpfCnpj());
        defendant.setAddress(inputCleaner.removeAccents(defendantRequestDto.getAddress()));
        defendant.setState(state);
        defendant.setCity(city);
        defendant.setNeighborhood(inputCleaner.removeAccents(defendantRequestDto.getNeighborhood()));
        defendant.setUf(city.getUf());
        defendant.setCep(defendantRequestDto.getCep());
        defendant.setContact(inputCleaner.removeAccents(defendantRequestDto.getContact()));
        defendant.setEmail(inputCleaner.removeAccents(defendantRequestDto.getEmail()));

        return defendant;
    }
}
