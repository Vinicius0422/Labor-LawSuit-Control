package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.SaveOrUpdateResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.OnlyClaimantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.AccountType;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Claimant;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.MaritalStatus;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Nationality;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.ClaimantService;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

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
    @Autowired
    private AnnotationRepository annotationRepository;
    @Autowired
    private AttorneyRepository attorneyRepository;
    @Autowired
    private DefendantRepository defendantRepository;
    @Autowired
    private LawsuitRepository lawsuitRepository;
    @Autowired
    private ProgressRepository progressRepository;

    @Override
    public ResponseDefault getAllClaimants() {
        List<OnlyClaimantResponseDto> claimants = claimantRepository.findAllClaimants();
        if(claimants == null){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No records found!", null);
        }
        return new ResponseDefault<>(HttpStatus.OK, "Search carried out!", claimants);
    }

    @Override
    public ResponseDefault getClaimantById(Long claimantId) {
        var claimant = claimantRepository.findClaimantById(claimantId).orElse(null);
        if(claimant == null){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No records found for this ID!", null);
        }
        var lawsuitsDto = lawsuitRepository.findLawsuitByClaimantId(claimantId);
        claimant.setLawsuits(lawsuitsDto);
        return new ResponseDefault(HttpStatus.OK, "Search carried out!", claimant);
    }

    @Override
    public ResponseDefault getByCpf(String cpf) {
        String cpfFormatted = cleanseNumericInput(cpf);
        System.out.println("Formatted CPF: " + cpfFormatted); // Adicione esta linha para verificar o CPF formatado
        if(cpfFormatted.length() != 11){
            return new ResponseDefault(HttpStatus.BAD_REQUEST, "The CPF provided does not meet the requirements");
        }
        var claimant = claimantRepository.findClaimantByCpf(cpfFormatted).orElse(null);
        if(claimant == null){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No records found for this CPF!", null);
        }
        var lawsuitsDto = lawsuitRepository.findLawsuitByClaimantId(claimant.getClaimantId());
        claimant.setLawsuits(lawsuitsDto);
        return new ResponseDefault<>(HttpStatus.OK, "Search carried out!", claimant);
    }

    @Override
    public ResponseDefault getByName(String claimantName) {
        if(claimantName.length() < 3){
            return new ResponseDefault<>(HttpStatus.BAD_REQUEST,
                    "Enter a name with at least 3 characters!", null);
        }
        var claimants = claimantRepository.findClaimantByNameContains(claimantName);
        if(claimants.isEmpty()){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No records found for this name!", null);
        }
        for (int i = 0; i < claimants.size(); i++) {
            var lawsuitsDto = lawsuitRepository.findLawsuitByClaimantId(claimants.get(i).getClaimantId());
            claimants.get(i).setLawsuits(lawsuitsDto);
        }
        return new ResponseDefault<>(HttpStatus.OK, "Search carried out!", claimants);
    }


    @Override
    public SaveOrUpdateResponseDefault saveClaimant(ClaimantRequestDto claimantRequestDto) {
        var validation = validationClaimantRequestDto(claimantRequestDto);
        if(!validation.isEmpty()){
            return new SaveOrUpdateResponseDefault(HttpStatus.CONFLICT, validation);
        }
        Claimant claimant = createClaimantFromDto(claimantRequestDto);
        claimant.setCreatedAt(LocalDateTime.now());
        claimantRepository.save(claimant);
        return new SaveOrUpdateResponseDefault(HttpStatus.CREATED, Collections.singletonList("Successfully created!"));
    }

    @Override
    public SaveOrUpdateResponseDefault updateClaimant(Long id, ClaimantRequestDto claimantRequestDto) {
        var claimantToUpdate = claimantRepository.findById(id).orElse(null);
        if(claimantToUpdate == null){
            return new SaveOrUpdateResponseDefault(HttpStatus.NOT_FOUND, Collections.singletonList("No records found for this ID!"));
        }
        var validation = validationClaimantRequestDtoUpdate(id, claimantRequestDto);
        if(!validation.isEmpty()) {
            return  new SaveOrUpdateResponseDefault(HttpStatus.CONFLICT, validation);
        }
        Claimant existingClaimant = claimantToUpdate;
        updateClaimantFields(existingClaimant, claimantRequestDto);
        existingClaimant.setUpdatedAt(LocalDateTime.now());
        claimantRepository.save(existingClaimant);
        return new SaveOrUpdateResponseDefault(HttpStatus.OK, Collections.singletonList("Updated successfully!"));
    }

    public String cleanseNumericInput(String input){
        return input.replaceAll("[^0-9]", "");
    }

    public void updateClaimantFields(Claimant existingClaimant, ClaimantRequestDto claimantRequestDto){
        existingClaimant.setClaimantName(claimantRequestDto.getClaimantName());
        existingClaimant.setBirthDate(parseLocalDate(claimantRequestDto.getBirthDate()));
        existingClaimant.setOccupation(claimantRequestDto.getOccupation());
        existingClaimant.setCtps(claimantRequestDto.getCtps());
        existingClaimant.setSerieCtps(claimantRequestDto.getSerieCtps());
        existingClaimant.setRg(cleanseNumericInput(claimantRequestDto.getRg()));
        existingClaimant.setOrgaoRg(claimantRequestDto.getOrgaoRg());
        existingClaimant.setCpf(cleanseNumericInput(claimantRequestDto.getCpf()));
        existingClaimant.setPis(claimantRequestDto.getPis());
        existingClaimant.setAddress(claimantRequestDto.getAddress());
        existingClaimant.setCity(claimantRequestDto.getCity());
        existingClaimant.setNeighborhood(claimantRequestDto.getNeighborhood());
        existingClaimant.setUf(claimantRequestDto.getUf());
        existingClaimant.setCep(cleanseNumericInput(claimantRequestDto.getCep()));
        existingClaimant.setBank(claimantRequestDto.getBank());
        existingClaimant.setAgency(cleanseNumericInput(claimantRequestDto.getAgency()));
        existingClaimant.setOperation(claimantRequestDto.getOperation());
        existingClaimant.setAccount(cleanseNumericInput(claimantRequestDto.getAccount()));
        existingClaimant.setContact(cleanseNumericInput(claimantRequestDto.getContact()));
        existingClaimant.setEmail(claimantRequestDto.getEmail());
        // Setar outras propriedades conforme necessário
        existingClaimant.setNationality(nationalityRepository.findById(claimantRequestDto.getNationalityId()).orElse(null));
        existingClaimant.setMaritalStatus(maritalStatusRepository.findById(claimantRequestDto.getMaritalStatusId()).orElse(null));
        existingClaimant.setAccountType(accountTypeRepository.findById(claimantRequestDto.getAccountTypeId()).orElse(null));
    }

    private LocalDate parseLocalDate(String birthDate) {
        return LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public List<String> validationClaimantRequestDto(ClaimantRequestDto claimantRequestDto){
        List<String> errors = new ArrayList<>();
        if(claimantRequestDto.getClaimantName().isBlank()){
            errors.add("The name field is required!");
        }
        if(claimantRequestDto.getClaimantName().length() < 3){
            errors.add("Enter a name with at least 3 characters!");
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
        String cpfFormatted = cleanseNumericInput(claimantRequestDto.getCpf());
        if (claimantRepository.existsByCpf(cpfFormatted)){
            errors.add("This CPF already registered!");
        }
        String rgFormatted = cleanseNumericInput(claimantRequestDto.getRg());
        if(claimantRepository.existsByRg(rgFormatted)){
            errors.add("This RG already registered!");
        }
        String bank = claimantRequestDto.getBank();
        String agencyFormatted = cleanseNumericInput(claimantRequestDto.getAgency());
        String accountFormatted = cleanseNumericInput(claimantRequestDto.getAccount());
        if(claimantRepository.existsByBankAndAgencyAndAccount(bank, agencyFormatted, accountFormatted)){
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
        String cpfFormatted = cleanseNumericInput(claimantRequestDto.getCpf());
        boolean cpfCheck = claimantRepository.existsByCpf(cpfFormatted);
        if(cpfCheck) {
            Long cpfId = claimantRepository.findByCpfEquals(cpfFormatted).getId();
            if (cpfId != id) {
                errors.add("This CPF already registered!");
            }
        }
        String rgFormatted = cleanseNumericInput(claimantRequestDto.getRg());
        boolean rgCheck = claimantRepository.existsByRg(rgFormatted);
        if(rgCheck) {
            Long rgId = claimantRepository.findByRgEquals(rgFormatted).getId();
            if (rgId != id) {
                errors.add("This RG already registered!");
            }
        }
        String bank = claimantRequestDto.getBank();
        String agencyFormatted = cleanseNumericInput(claimantRequestDto.getAgency());
        String accountFormatted = cleanseNumericInput(claimantRequestDto.getAccount());
        boolean contaBancoCheck = claimantRepository.existsByBankAndAgencyAndAccount(bank,
               agencyFormatted, accountFormatted);
        if(contaBancoCheck) {
            Long contaId = claimantRepository.findByBankAndAgencyAndAccountEquals(bank,
                    agencyFormatted, accountFormatted).getId();
            if (contaId != id) {
                errors.add("This bank account already registered!");
            }
        }
        return errors;
    }

    public static String removeAccents(String input) {
        if (input == null) {
            return null;
        }

        // Normalize to canonical decomposition form
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);

        // Remove diacritics (accents) using a regular expression
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");
        }
    
    public Claimant createClaimantFromDto(ClaimantRequestDto claimantRequestDto){
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Nationality nationality = nationalityRepository.findById(claimantRequestDto.getNationalityId()).orElse(null);
        MaritalStatus maritalStatus = maritalStatusRepository.findById(claimantRequestDto.getMaritalStatusId()).orElse(null);
        AccountType accountType = accountTypeRepository.findById(claimantRequestDto.getAccountTypeId()).orElse(null);

        Claimant claimant = new Claimant();
        claimant.setClaimantName(removeAccents(claimantRequestDto.getClaimantName()));
        claimant.setBirthDate(LocalDate.parse(claimantRequestDto.getBirthDate(), formatterDate));
        claimant.setOccupation(claimantRequestDto.getOccupation());
        claimant.setCtps(claimantRequestDto.getCtps());
        claimant.setSerieCtps(claimantRequestDto.getSerieCtps());
        claimant.setRg(cleanseNumericInput(claimantRequestDto.getRg()));
        claimant.setOrgaoRg(claimantRequestDto.getOrgaoRg());
        claimant.setCpf(cleanseNumericInput(claimantRequestDto.getCpf()));
        claimant.setPis(claimantRequestDto.getPis());
        claimant.setAddress(claimantRequestDto.getAddress());
        claimant.setCity(claimantRequestDto.getCity());
        claimant.setNeighborhood(claimantRequestDto.getNeighborhood());
        claimant.setUf(claimantRequestDto.getUf());
        claimant.setCep(cleanseNumericInput(claimantRequestDto.getCep()));
        claimant.setBank(claimantRequestDto.getBank());
        claimant.setAgency(cleanseNumericInput(claimantRequestDto.getAgency()));
        claimant.setOperation(claimantRequestDto.getOperation());
        claimant.setAccount(cleanseNumericInput(claimantRequestDto.getAccount()));
        claimant.setContact(cleanseNumericInput(claimantRequestDto.getContact()));
        claimant.setEmail(claimantRequestDto.getEmail());
        claimant.setNationality(nationality);
        claimant.setMaritalStatus(maritalStatus);
        claimant.setAccountType(accountType);

        return claimant;
    }
}