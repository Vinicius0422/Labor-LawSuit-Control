package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.SaveOrUpdateResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantServiceResponse;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.OnlyClaimantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.AccountType;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Claimant;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.MaritalStatus;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Nationality;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.ClaimantService;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.AccountTypeRepository;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.ClaimantRepository;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.MaritalStatusRepository;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.NationalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ClaimantServiceImpl implements ClaimantService {

    @Autowired
    private ClaimantRepository claimantRepository;

    @Autowired
    private NationalityRepository nationalityRepository;

    @Autowired
    private MaritalStatusRepository maritalStatusRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Override
    public ClaimantServiceResponse<List<OnlyClaimantResponseDto>> getAllClaimants() {
        List<OnlyClaimantResponseDto> claimantResponseDtos = claimantRepository.findAllClaimants();
        return new ClaimantServiceResponse<>(HttpStatus.OK, "Search carried out!", claimantResponseDtos);
    }

    @Override
    public ClaimantServiceResponse getClaimantById(Long claimantId) {
        var claimant = claimantRepository.findClaimantById(claimantId);
        return new ClaimantServiceResponse(HttpStatus.OK, "Search carried out!", claimant.orElse(null));
    }

    @Override
    public ClaimantServiceResponse getByCpf(String cpf) {
        if(! claimantCpfFormatValidation(cpf)){
            return new ClaimantServiceResponse(HttpStatus.BAD_REQUEST, "The CPF provided does not meet the requirements");
        }
        String cpfFormatted = cpf.replaceAll("[^0-9]", "");
        var claimant = claimantRepository.findClaimantByCpf(cpfFormatted);
        return new ClaimantServiceResponse<>(HttpStatus.OK, "Search carried out!", claimant.orElse(null));
    }

    public boolean claimantCpfFormatValidation(String cpf){
        //Checks if the CPF meets the regular expression;
        return cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}");
    }

    @Override
    public ClaimantServiceResponse<List<ClaimantResponseDto>> getByName(String claimantName) {
        if(!claimantNameFormatValidation(claimantName)){
            return new ClaimantServiceResponse<>(HttpStatus.BAD_REQUEST,
                    "The name must contain only letters, spaces and accents, and be at least 3 characters long!", null);
        }
        var claimants = claimantRepository.findClaimantByNameContains(claimantName);
        return new ClaimantServiceResponse<>(HttpStatus.OK, "Search carried out!", claimants);
    }

    public boolean claimantNameFormatValidation(String claimantName){
        //Defines a regular expression that allows only letters (upper or lower case) and spaces and at least 3 letters;
        String regex = "^[\\p{L} \\s]{3,}$";
        //Checks if the name matches the regular expression;
        return claimantName.matches(regex);
    }

    @Override
    public SaveOrUpdateResponseDefault saveClaimant(ClaimantRequestDto claimantRequestDto) {
        var validation = validationClaimantRequestDto(claimantRequestDto);
        if(!validation.isEmpty()){
            return new SaveOrUpdateResponseDefault(HttpStatus.CONFLICT, validation);
        }
        Claimant claimant = DtoToEntity(claimantRequestDto);
        claimant.setCreatedAt(LocalDateTime.now());
        claimantRepository.save(claimant);
        return new SaveOrUpdateResponseDefault(HttpStatus.CREATED, Collections.singletonList("Successfully created!"));
    }

    @Override
    public SaveOrUpdateResponseDefault updateClaimant(Long id, ClaimantRequestDto claimantRequestDto) {
        var claimantToUpdate = claimantRepository.findById(id);
        if(claimantToUpdate.isEmpty()){
            return new SaveOrUpdateResponseDefault(HttpStatus.NOT_FOUND, Collections.singletonList("No records found for this ID!"));
        }
        var validation = validationClaimantRequestDtoUpdate(id, claimantRequestDto);
        if(!validation.isEmpty()) {
            return  new SaveOrUpdateResponseDefault(HttpStatus.CONFLICT, validation);
        }
        Claimant existingClaimant = claimantToUpdate.get();
        updateClaimantFields(existingClaimant, claimantRequestDto);
        existingClaimant.setUpdatedAt(LocalDateTime.now());
        claimantRepository.save(existingClaimant);
        return new SaveOrUpdateResponseDefault(HttpStatus.OK, Collections.singletonList("Updated successfully!"));
    }
    public void updateClaimantFields(Claimant existingClaimant, ClaimantRequestDto claimantRequestDto){
        String cpfFormatted = claimantRequestDto.getCpf().replaceAll("[^0-9]", "");
        existingClaimant.setClaimantName(claimantRequestDto.getClaimantName());
        existingClaimant.setBirthDate(parseLocalDate(claimantRequestDto.getBirthDate()));
        existingClaimant.setOccupation(claimantRequestDto.getOccupation());
        existingClaimant.setCtps(claimantRequestDto.getCtps());
        existingClaimant.setSerieCtps(claimantRequestDto.getSerieCtps());
        existingClaimant.setRg(claimantRequestDto.getRg());
        existingClaimant.setOrgaoRg(claimantRequestDto.getOrgaoRg());
        existingClaimant.setCpf(cpfFormatted);
        existingClaimant.setPis(claimantRequestDto.getPis());
        existingClaimant.setAddress(claimantRequestDto.getAddress());
        existingClaimant.setCity(claimantRequestDto.getCity());
        existingClaimant.setNeighborhood(claimantRequestDto.getNeighborhood());
        existingClaimant.setUf(claimantRequestDto.getUf());
        existingClaimant.setCep(claimantRequestDto.getCep());
        existingClaimant.setBank(claimantRequestDto.getBank());
        existingClaimant.setAgency(claimantRequestDto.getAgency());
        existingClaimant.setOperation(claimantRequestDto.getOperation());
        existingClaimant.setAccount(claimantRequestDto.getAccount());
        existingClaimant.setContact(claimantRequestDto.getContact());
        existingClaimant.setEmail(claimantRequestDto.getEmail());
        // Setar outras propriedades conforme necessário
        existingClaimant.setNationalityId(nationalityRepository.findById(claimantRequestDto.getNationalityId()).orElse(null));
        existingClaimant.setMaritalStatusId(maritalStatusRepository.findById(claimantRequestDto.getMaritalStatusId()).orElse(null));
        existingClaimant.setAccountTypeId(accountTypeRepository.findById(claimantRequestDto.getAccountTypeId()).orElse(null));
    }

    private LocalDate parseLocalDate(String birthDate) {
        return LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public List<String> validationClaimantRequestDto(ClaimantRequestDto claimantRequestDto){
        List<String> errors = new ArrayList<>();
        if(claimantRequestDto.getClaimantName().isBlank()){
            errors.add("The name field is required!");
        }
        if(claimantRequestDto.getCtps().isBlank()){
            errors.add("The CTPS field is required!");
        }
        if(claimantRequestDto.getSerieCtps().isBlank()){
            errors.add("The serie CTPS field is required!");
        }
        if(claimantRequestDto.getRg().isBlank()){
            errors.add("The RG field is required!");
        }
        if(claimantRequestDto.getOrgaoRg().isBlank()){
            errors.add("The orgão RG field is required!");
        }
        if(claimantRequestDto.getCpf().isBlank()){
            errors.add("The CPF field is required!");
        }
        if (claimantRequestDto.getAddress().isBlank()) {
            errors.add("The address field is required!");
        }
        if (claimantRequestDto.getCity().isBlank()) {
            errors.add("The city field is required!");
        }
        if(claimantRequestDto.getNeighborhood().isBlank()){
            errors.add("The neighborhood field is required!");
        }
        if(claimantRequestDto.getUf().isBlank()){
            errors.add("The UF field is required!");
        }
        if (claimantRequestDto.getCep().isBlank()) {
            errors.add("The CEP field is required!");
        }
        if (claimantRequestDto.getContact().isBlank()) {
            errors.add("The contact field is required!");
        }
        String cpfFormatted = claimantRequestDto.getCpf().replaceAll("[^0-9]", "");
        if (claimantRepository.existsByCpf(cpfFormatted)){
            errors.add("This CPF already registered!");
        }
        if(claimantRepository.existsByRg(claimantRequestDto.getRg())){
            errors.add("This RG already registered!");
        }
        if(claimantRepository.existsByBankAndAgencyAndAccount(claimantRequestDto.getBank(), claimantRequestDto.getAgency(), claimantRequestDto.getAccount())){
            errors.add("This bank account already registered!");
        }
        return errors;
    }

    public List<String> validationClaimantRequestDtoUpdate(Long id, ClaimantRequestDto claimantRequestDto){
        List<String> errors = new ArrayList<>();
        if(claimantRequestDto.getClaimantName().isBlank()){
            errors.add("The name field is required!");
        }
        if(claimantRequestDto.getCtps().isBlank()){
            errors.add("The CTPS field is required!");
        }
        if(claimantRequestDto.getSerieCtps().isBlank()){
            errors.add("The serie CTPS field is required!");
        }
        if(claimantRequestDto.getRg().isBlank()){
            errors.add("The RG field is required!");
        }
        if(claimantRequestDto.getOrgaoRg().isBlank()){
            errors.add("The orgão RG field is required!");
        }
        if(claimantRequestDto.getCpf().isBlank()){
            errors.add("The CPF field is required!");
        }
        if (claimantRequestDto.getAddress().isBlank()) {
            errors.add("The address field is required!");
        }
        if (claimantRequestDto.getCity().isBlank()) {
            errors.add("The city field is required!");
        }
        if(claimantRequestDto.getNeighborhood().isBlank()){
            errors.add("The neighborhood field is required!");
        }
        if(claimantRequestDto.getUf().isBlank()){
            errors.add("The UF field is required!");
        }
        if (claimantRequestDto.getCep().isBlank()) {
            errors.add("The CEP field is required!");
        }
        if (claimantRequestDto.getContact().isBlank()) {
            errors.add("The contact field is required!");
        }
        String cpfFormatted = claimantRequestDto.getCpf().replaceAll("[^0-9]", "");
        boolean cpfCheck = claimantRepository.existsByCpf(cpfFormatted);
        if(cpfCheck) {
            Long cpfId = claimantRepository.findByCpfEquals(cpfFormatted).getId();
            if (cpfId != id) {
                errors.add("This CPF already registered!");
            }
        }
        boolean rgCheck = claimantRepository.existsByRg(claimantRequestDto.getRg());
        if(rgCheck) {
            Long rgId = claimantRepository.findByRgEquals(claimantRequestDto.getRg()).getId();
            if (rgId != id) {
                errors.add("This RG already registered!");
            }
        }
        boolean contaBancoCheck = claimantRepository.existsByBankAndAgencyAndAccount(claimantRequestDto.getBank(),
                claimantRequestDto.getAgency(), claimantRequestDto.getAccount());
        if(contaBancoCheck) {
            Long contaId = claimantRepository.findByBankAndAgencyAndAccountEquals(claimantRequestDto.getBank(),
                    claimantRequestDto.getAgency(), claimantRequestDto.getAccount()).getId();
            if (contaId != id) {
                errors.add("This bank account already registered!");
            }
        }
        return errors;
    }
    
    public Claimant DtoToEntity(ClaimantRequestDto claimantRequestDto){
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String cpfFormatted = claimantRequestDto.getCpf().replaceAll("[^0-9]", "");
        Nationality nationality = nationalityRepository.findById(claimantRequestDto.getNationalityId()).orElse(null);
        MaritalStatus maritalStatus = maritalStatusRepository.findById(claimantRequestDto.getMaritalStatusId()).orElse(null);
        AccountType accountType = accountTypeRepository.findById(claimantRequestDto.getAccountTypeId()).orElse(null);

        Claimant claimant = new Claimant();
        claimant.setClaimantName(claimantRequestDto.getClaimantName());
        claimant.setBirthDate(LocalDate.parse(claimantRequestDto.getBirthDate(), formatterDate));
        claimant.setOccupation(claimantRequestDto.getOccupation());
        claimant.setCtps(claimantRequestDto.getCtps());
        claimant.setSerieCtps(claimantRequestDto.getSerieCtps());
        claimant.setRg(claimantRequestDto.getRg());
        claimant.setOrgaoRg(claimantRequestDto.getOrgaoRg());
        claimant.setCpf(cpfFormatted);
        claimant.setPis(claimantRequestDto.getPis());
        claimant.setAddress(claimantRequestDto.getAddress());
        claimant.setCity(claimantRequestDto.getCity());
        claimant.setNeighborhood(claimantRequestDto.getNeighborhood());
        claimant.setUf(claimantRequestDto.getUf());
        claimant.setCep(claimantRequestDto.getCep());
        claimant.setBank(claimantRequestDto.getBank());
        claimant.setAgency(claimantRequestDto.getAgency());
        claimant.setOperation(claimantRequestDto.getOperation());
        claimant.setAccount(claimantRequestDto.getAccount());
        claimant.setContact(claimantRequestDto.getContact());
        claimant.setEmail(claimantRequestDto.getEmail());
        claimant.setNationalityId(nationality);
        claimant.setMaritalStatusId(maritalStatus);
        claimant.setAccountTypeId(accountType);

        return claimant;
    }
}
