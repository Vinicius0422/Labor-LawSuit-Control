package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.SaveOrUpdateResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.*;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.ClaimantService;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.utils.InputCleaner;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.validations.ClaimantValidator;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.validations.DateFormatValidator;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class ClaimantServiceImpl implements ClaimantService {

    private ClaimantRepository claimantRepository;
    private NationalityRepository nationalityRepository;
    private MaritalStatusRepository maritalStatusRepository;
    private AccountTypeRepository accountTypeRepository;
    private AnnotationRepository annotationRepository;
    private AttorneyRepository attorneyRepository;
    private DefendantRepository defendantRepository;
    private LawsuitRepository lawsuitRepository;
    private ProgressRepository progressRepository;
    private InputCleaner inputCleaner;
    private ClaimantValidator claimantValidator;
    private StateRepository stateRepository;
    private CityRepository cityRepository;
    private DateFormatValidator dateFormatValidator;

    public ClaimantServiceImpl(ClaimantRepository claimantRepository, NationalityRepository nationalityRepository,
                               MaritalStatusRepository maritalStatusRepository, AccountTypeRepository accountTypeRepository,
                               AnnotationRepository annotationRepository, AttorneyRepository attorneyRepository,
                               DefendantRepository defendantRepository, LawsuitRepository lawsuitRepository,
                               ProgressRepository progressRepository, InputCleaner inputCleaner, ClaimantValidator claimantValidator,
                               StateRepository stateRepository, CityRepository cityRepository, DateFormatValidator dateFormatValidator) {
        this.claimantRepository = claimantRepository;
        this.nationalityRepository = nationalityRepository;
        this.maritalStatusRepository = maritalStatusRepository;
        this.accountTypeRepository = accountTypeRepository;
        this.annotationRepository = annotationRepository;
        this.attorneyRepository = attorneyRepository;
        this.defendantRepository = defendantRepository;
        this.lawsuitRepository = lawsuitRepository;
        this.progressRepository = progressRepository;
        this.inputCleaner = inputCleaner;
        this.claimantValidator = claimantValidator;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.dateFormatValidator = dateFormatValidator;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDefault getAllClaimants(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var claimants = claimantRepository.findAllClaimants(pageable);
        if(claimants.isEmpty()){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No records found!", claimants);
        }
        return new ResponseDefault(HttpStatus.OK, "Search carried out!", claimants);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDefault getClaimantById(Long claimantId) {
        var claimant = claimantRepository.findClaimantById(claimantId).orElse(null);
        if(claimant == null){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No records found!", null);
        }
        var lawsuitsDto = lawsuitRepository.findLawsuitByClaimantId(claimantId);
        claimant.setLawsuits(lawsuitsDto);
        return new ResponseDefault(HttpStatus.OK, "Search carried out!", claimant);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDefault getByCpf(String cpf) {
        String cpfFormatted = inputCleaner.cleanseNumericInput(cpf);
        if(cpfFormatted.length() != 11){
            return new ResponseDefault(HttpStatus.BAD_REQUEST, "The CPF provided does not meet the requirements");
        }
        var claimant = claimantRepository.findClaimantByCpf(cpfFormatted).orElse(null);
        if(claimant == null){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No records found!", null);
        }
        var lawsuitsDto = lawsuitRepository.findLawsuitByClaimantId(claimant.getClaimantId());
        claimant.setLawsuits(lawsuitsDto);
        return new ResponseDefault<>(HttpStatus.OK, "Search carried out!", claimant);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDefault getByName(String claimantName) {
        if(claimantName.length() < 3){
            return new ResponseDefault<>(HttpStatus.BAD_REQUEST, "Enter a name with at least 3 characters!", null);
        }
        var claimants = claimantRepository.findClaimantByNameContains(claimantName);
        if(claimants.isEmpty()){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No records found!", null);
        }
        for (int i = 0; i < claimants.size(); i++) {
            var lawsuitsDto = lawsuitRepository.findLawsuitByClaimantId(claimants.get(i).getClaimantId());
            claimants.get(i).setLawsuits(lawsuitsDto);
        }
        return new ResponseDefault<>(HttpStatus.OK, "Search carried out!", claimants);
    }


    @Override
    @Transactional
    public SaveOrUpdateResponseDefault saveClaimant(ClaimantRequestDto claimantRequestDto) {
        var validation = claimantValidator.validationSaveClaimantRequestDto(claimantRequestDto);
        if(!validation.isEmpty()){
            return new SaveOrUpdateResponseDefault(HttpStatus.UNPROCESSABLE_ENTITY, validation);
        }
        Claimant claimant = createClaimantFromDto(claimantRequestDto);
        claimant.setCreatedAt(LocalDateTime.now());
        claimantRepository.save(claimant);
        return new SaveOrUpdateResponseDefault(HttpStatus.CREATED, Collections.singletonList("Successfully created!"));
    }

    @Override
    @Transactional
    public SaveOrUpdateResponseDefault updateClaimant(Long id, ClaimantRequestDto claimantRequestDto) {
        var claimantToUpdate = claimantRepository.findById(id).orElse(null);
        if(claimantToUpdate == null){
            return new SaveOrUpdateResponseDefault(HttpStatus.NOT_FOUND , Collections.singletonList("No records found!"));
        }
        var validation = claimantValidator.validationUpdateClaimantRequestDto(id, claimantRequestDto);
        if(!validation.isEmpty()) {
            return  new SaveOrUpdateResponseDefault(HttpStatus.UNPROCESSABLE_ENTITY, validation);
        }
        Claimant existingClaimant = claimantToUpdate;
        updateClaimantFields(existingClaimant, claimantRequestDto);
        existingClaimant.setUpdatedAt(LocalDateTime.now());
        claimantRepository.save(existingClaimant);
        return new SaveOrUpdateResponseDefault(HttpStatus.OK, Collections.singletonList("Updated successfully!"));
    }

    public void updateClaimantFields(Claimant existingClaimant, ClaimantRequestDto claimantRequestDto){
        Nationality nationality = nationalityRepository.findById(claimantRequestDto.getNationalityId()).orElse(null);
        MaritalStatus maritalStatus = maritalStatusRepository.findById(claimantRequestDto.getMaritalStatusId()).orElse(null);
        AccountType accountType = accountTypeRepository.findById(claimantRequestDto.getAccountTypeId()).orElse(null);
        State state = stateRepository.findById(claimantRequestDto.getStateId()).orElse(null);
        City city = cityRepository.findById(claimantRequestDto.getCityId()).orElse(null);
        existingClaimant.setClaimantName(claimantRequestDto.getClaimantName());
        existingClaimant.setBirthDate(dateFormatValidator.parseLocalDate(claimantRequestDto.getBirthDate()));
        existingClaimant.setOccupation(claimantRequestDto.getOccupation());
        existingClaimant.setCtps(claimantRequestDto.getCpf().substring(0,7));
        existingClaimant.setSerieCtps(claimantRequestDto.getCpf().substring(7,11));
        existingClaimant.setRg(claimantRequestDto.getRg());
        existingClaimant.setOrgaoRg(claimantRequestDto.getOrgaoRg());
        existingClaimant.setCpf(claimantRequestDto.getCpf());
        existingClaimant.setPis(claimantRequestDto.getPis());
        existingClaimant.setAddress(claimantRequestDto.getAddress());
        existingClaimant.setNeighborhood(claimantRequestDto.getNeighborhood());
        existingClaimant.setCep(claimantRequestDto.getCep());
        existingClaimant.setBank(claimantRequestDto.getBank());
        existingClaimant.setAgency(claimantRequestDto.getAgency());
        existingClaimant.setOperation(claimantRequestDto.getOperation());
        existingClaimant.setAccount(claimantRequestDto.getAccount());
        existingClaimant.setContact(claimantRequestDto.getContact());
        existingClaimant.setEmail(claimantRequestDto.getEmail());
        // Setar outras propriedades conforme necessário
        existingClaimant.setNationality(nationality);
        existingClaimant.setMaritalStatus(maritalStatus);
        existingClaimant.setAccountType(accountType);
        existingClaimant.setState(state);
        existingClaimant.setCity(city);
        existingClaimant.setUf(city.getUf());
    }

    public Claimant createClaimantFromDto(ClaimantRequestDto claimantRequestDto){
        Nationality nationality = nationalityRepository.findById(claimantRequestDto.getNationalityId()).orElse(null);
        MaritalStatus maritalStatus = maritalStatusRepository.findById(claimantRequestDto.getMaritalStatusId()).orElse(null);
        AccountType accountType = accountTypeRepository.findById(claimantRequestDto.getAccountTypeId()).orElse(null);
        State state = stateRepository.findById(claimantRequestDto.getStateId()).orElse(null);
        City city = cityRepository.findById(claimantRequestDto.getCityId()).orElse(null);

        Claimant claimant = new Claimant();
        claimant.setClaimantName(inputCleaner.removeAccents(claimantRequestDto.getClaimantName()));
        claimant.setBirthDate(dateFormatValidator.parseLocalDate(claimantRequestDto.getBirthDate()));
        claimant.setOccupation(claimantRequestDto.getOccupation());
        claimant.setCtps(claimantRequestDto.getCpf().substring(0,7));
        claimant.setSerieCtps(claimantRequestDto.getCpf().substring(8,11));
        claimant.setRg(claimantRequestDto.getRg());
        claimant.setOrgaoRg(claimantRequestDto.getOrgaoRg());
        claimant.setCpf(claimantRequestDto.getCpf());
        claimant.setPis(claimantRequestDto.getPis());
        claimant.setAddress(claimantRequestDto.getAddress());
        claimant.setNeighborhood(claimantRequestDto.getNeighborhood());
        claimant.setCep(claimantRequestDto.getCep());
        claimant.setBank(claimantRequestDto.getBank());
        claimant.setAgency(claimantRequestDto.getAgency());
        claimant.setOperation(claimantRequestDto.getOperation());
        claimant.setAccount(claimantRequestDto.getAccount());
        claimant.setContact(claimantRequestDto.getContact());
        claimant.setEmail(claimantRequestDto.getEmail());
        claimant.setNationality(nationality);
        claimant.setMaritalStatus(maritalStatus);
        claimant.setAccountType(accountType);
        claimant.setState(state);
        claimant.setCity(city);
        claimant.setUf(city.getUf());

        return claimant;
    }
}
