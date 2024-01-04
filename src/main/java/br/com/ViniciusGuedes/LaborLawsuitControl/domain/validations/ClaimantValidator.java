package br.com.ViniciusGuedes.LaborLawsuitControl.domain.validations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.utils.InputCleaner;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClaimantValidator {

    @Autowired
    private ClaimantRepository claimantRepository;
    @Autowired
    private InputCleaner inputCleaner;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private NationalityRepository nationalityRepository;
    @Autowired
    private MaritalStatusRepository maritalStatusRepository;
    @Autowired
    private AccountTypeRepository accountTypeRepository;
    @Autowired
    private DateFormatValidator dateFormatValidator;


    public List<String> validationSaveClaimantRequestDto(ClaimantRequestDto claimantRequestDto){
        List<String> errors = new ArrayList<>();
        if(claimantRequestDto.getClaimantName() == null || claimantRequestDto.getClaimantName().isBlank()){
            errors.add("The name is required!");
        }
        if(claimantRequestDto.getClaimantName().length() < 3){
            errors.add("Enter a name with at least 3 characters!");
        }
        if(claimantRequestDto.getBirthDate() == null || !dateFormatValidator.isValidDate(claimantRequestDto.getBirthDate())) {
            errors.add("Invalid birth date format! Use the format dd/MM/yyyy.");
        }
        if(claimantRequestDto.getOccupation().length() > 50){
            errors.add("The Occupation field can have a maximum of 50 characters");
        }
        if(claimantRequestDto.getRg() == null || claimantRequestDto.getRg().isBlank()){
            errors.add("The RG field is required!");
        }
        var rgFormatted = inputCleaner.cleanseNumericInput(claimantRequestDto.getRg());
        if(rgFormatted.length() > 11){
            errors.add("The rg field can have a maximum of 11 characters");
        }
        if(claimantRepository.existsByRg(rgFormatted)){
            errors.add("This RG already registered!");
        }
        claimantRequestDto.setRg(rgFormatted);
        if(claimantRequestDto.getOrgaoRg() == null || claimantRequestDto.getOrgaoRg().isBlank()){
            errors.add("The orgão RG field is required!");
        }
        if(claimantRequestDto.getOrgaoRg().length() > 10){
            errors.add("The rg field can have a maximum of 10 characters");
        }
        if(claimantRequestDto.getCpf() == null || claimantRequestDto.getCpf().isBlank()){
            errors.add("The CPF field is required!");
        }
        var cpfFormatted = inputCleaner.cleanseNumericInput(claimantRequestDto.getCpf());
        if(cpfFormatted.length() > 11){
            errors.add("The CPF can have a maximum of 11 characters");
        }
        if (claimantRepository.existsByCpf(cpfFormatted)){
            errors.add("This CPF already registered!");
        }
        claimantRequestDto.setCpf(cpfFormatted);
        var pisFormatted = inputCleaner.cleanseNumericInput(claimantRequestDto.getPis());
        if(pisFormatted.length() > 50){
            errors.add("The PIS can have a maximum of 50 characters");
        }
        claimantRequestDto.setPis(pisFormatted);
        if(claimantRequestDto.getAddress() == null || claimantRequestDto.getAddress().isBlank()) {
            errors.add("The address field is required!");
        }
        if(claimantRequestDto.getAddress().length() > 255){
            errors.add("The Address field can have a maximum of 255 characters");
        }
        if(claimantRequestDto.getStateId() == null || claimantRequestDto.getStateId().describeConstable().isEmpty()) {
            errors.add("The state field is required!");
        }
        if(!errors.contains("The state field is required!") && !stateRepository.existsById(claimantRequestDto.getStateId())){
            errors.add("No state found with the given id!");
        }
        if(claimantRequestDto.getCityId() == null || claimantRequestDto.getCityId().describeConstable().isEmpty()) {
            errors.add("The city field is required!");
        }
        if(!errors.contains("The city field is required!") && !cityRepository.existsById(claimantRequestDto.getCityId())){
            errors.add("No city found with the given id!");
        }
        if(claimantRequestDto.getAccountTypeId() == null || claimantRequestDto.getAccountTypeId().describeConstable().isEmpty()) {
            errors.add("The account type field is required!");
        }
        if(!errors.contains("The account type field is required!") && !accountTypeRepository.existsById(claimantRequestDto.getAccountTypeId())){
            errors.add("No account type found with the given id!");
        }
        if(claimantRequestDto.getNationalityId() == null || claimantRequestDto.getNationalityId().describeConstable().isEmpty()) {
            errors.add("The nationality field is required!");
        }
        if(!errors.contains("The nationality field is required!") && !nationalityRepository.existsById(claimantRequestDto.getNationalityId())){
            errors.add("No nationality found with the given id!");
        }
        if(claimantRequestDto.getMaritalStatusId() == null || claimantRequestDto.getMaritalStatusId().describeConstable().isEmpty()) {
            errors.add("The marital status field is required!");
        }
        if(!errors.contains("The marital status field is required!") && !maritalStatusRepository.existsById(claimantRequestDto.getMaritalStatusId())){
            errors.add("No marital status found with the given id!");
        }
        if(claimantRequestDto.getNeighborhood() == null ||claimantRequestDto.getNeighborhood().isBlank()){
            errors.add("The neighborhood field is required!");
        }
        if(claimantRequestDto.getNeighborhood().length() > 50){
            errors.add("The neighborhood can have a maximum of 50 characters");
        }
        if(claimantRequestDto.getCep() == null ||claimantRequestDto.getCep().isBlank()) {
            errors.add("The CEP field is required!");
        }
        var cepFormatted = inputCleaner.cleanseNumericInput(claimantRequestDto.getCep());
        if(cepFormatted.length() > 10){
            errors.add("The CEP can have a maximum of 10 characters");
        }
        claimantRequestDto.setCep(cepFormatted);
        if(claimantRequestDto.getBank().length() > 50){
            errors.add("The Bank can have a maximum of 50 characters");
        }
        var agencyFormatted = inputCleaner.cleanseNumericInput(claimantRequestDto.getAgency());
        var operationFormatedd = inputCleaner.cleanseNumericInput(claimantRequestDto.getOperation());
        var accountFormatted = inputCleaner.cleanseNumericInput(claimantRequestDto.getAccount());
        if(agencyFormatted.length() > 10){
            errors.add("The Agency can have a maximum of 10 characters");
        }
        if(operationFormatedd.length() > 10){
            errors.add("The Operation can have a maximum of 10 characters");
        }
        if(accountFormatted.length() > 20){
            errors.add("The Account can have a maximum of 20 characters");
        }
        String bank = claimantRequestDto.getBank();
        if(claimantRepository.existsByBankAndAgencyAndAccount(bank, agencyFormatted, accountFormatted)){
            errors.add("This bank account already registered!");
        }
        claimantRequestDto.setAgency(agencyFormatted);
        claimantRequestDto.setOperation(operationFormatedd);
        claimantRequestDto.setAccount(accountFormatted);
        if(claimantRequestDto.getContact() == null ||claimantRequestDto.getContact().isBlank()) {
            errors.add("The contact field is required!");
        }
        if(claimantRequestDto.getContact().length() > 255){
            errors.add("The Contact can have a maximum of 255 characters");
        }
        if(claimantRequestDto.getEmail().length() > 150){
            errors.add("The E-mail can have a maximum of 150 characters");
        }
        return errors;
    }

    public List<String> validationUpdateClaimantRequestDto(Long id, ClaimantRequestDto claimantRequestDto){
        List<String> errors = new ArrayList<>();
        if(claimantRequestDto.getClaimantName() == null || claimantRequestDto.getClaimantName().isBlank()){
            errors.add("The name field is required!");
        }
        if(claimantRequestDto.getClaimantName().length() < 3){
            errors.add("Enter a name with at least 3 characters!");
        }
        if(claimantRequestDto.getBirthDate() == null || !dateFormatValidator.isValidDate(claimantRequestDto.getBirthDate())) {
            errors.add("Invalid birth date format! Use the format dd/MM/yyyy.");
        }
        if(claimantRequestDto.getOccupation().length() > 50){
            errors.add("The Occupation field can have a maximum of 50 characters");
        }
        if(claimantRequestDto.getRg() == null ||claimantRequestDto.getRg().isBlank()){
            errors.add("The RG field is required!");
        }
        var rgFormatted = inputCleaner.cleanseNumericInput(claimantRequestDto.getRg());
        if(rgFormatted.length() > 11){
            errors.add("The rg field can have a maximum of 11 characters");
        }
        boolean rgCheck = claimantRepository.existsByRg(rgFormatted);
        if(rgCheck) {
            Long rgId = claimantRepository.findByRgEquals(rgFormatted).getClaimantId();
            if (rgId != id) {
                errors.add("This RG already registered!");
            }
        }
        claimantRequestDto.setRg(rgFormatted);
        if(claimantRequestDto.getOrgaoRg() == null ||claimantRequestDto.getOrgaoRg().isBlank()){
            errors.add("The orgão RG field is required!");
        }
        if(claimantRequestDto.getOrgaoRg().length() > 10){
            errors.add("The rg field can have a maximum of 10 characters");
        }
        if(claimantRequestDto.getCpf() == null ||claimantRequestDto.getCpf().isBlank()){
            errors.add("The CPF field is required!");
        }
        var cpfFormatted = inputCleaner.cleanseNumericInput(claimantRequestDto.getCpf());
        if(cpfFormatted.length() > 11){
            errors.add("The CPF field can have a maximum of 11 characters");
        }
        boolean cpfCheck = claimantRepository.existsByCpf(cpfFormatted);
        if(cpfCheck) {
            Long cpfId = claimantRepository.findByCpfEquals(cpfFormatted).getClaimantId();
            if (cpfId != id) {
                errors.add("This CPF already registered!");
            }
        }
        claimantRequestDto.setCpf(cpfFormatted);
        var pisFormatted = inputCleaner.cleanseNumericInput(claimantRequestDto.getPis());
        if(pisFormatted.length() > 50){
            errors.add("The PIS can have a maximum of 50 characters");
        }
        claimantRequestDto.setPis(pisFormatted);
        if(claimantRequestDto.getAddress() == null ||claimantRequestDto.getAddress().isBlank()) {
            errors.add("The address field is required!");
        }
        if(claimantRequestDto.getAddress().length() > 255){
            errors.add("The Address field can have a maximum of 255 characters");
        }
        if(claimantRequestDto.getStateId() == null || claimantRequestDto.getStateId().describeConstable().isEmpty()) {
            errors.add("The state field is required!");
        }
        if(!errors.contains("The state field is required!") && !stateRepository.existsById(claimantRequestDto.getStateId())){
            errors.add("No state found with the given id!");
        }
        if(claimantRequestDto.getCityId() == null || claimantRequestDto.getCityId().describeConstable().isEmpty()) {
            errors.add("The city field is required!");
        }
        if(!errors.contains("The city field is required!") && !cityRepository.existsById(claimantRequestDto.getCityId())){
            errors.add("No city found with the given id!");
        }if(claimantRequestDto.getAccountTypeId() == null || claimantRequestDto.getAccountTypeId().describeConstable().isEmpty()) {
            errors.add("The account type field is required!");
        }
        if(!errors.contains("The account type field is required!") && !accountTypeRepository.existsById(claimantRequestDto.getAccountTypeId())){
            errors.add("No account type found with the given id!");
        }
        if(claimantRequestDto.getNationalityId() == null || claimantRequestDto.getNationalityId().describeConstable().isEmpty()) {
            errors.add("The nationality field is required!");
        }
        if(!errors.contains("The nationality field is required!") && !nationalityRepository.existsById(claimantRequestDto.getNationalityId())){
            errors.add("No nationality found with the given id!");
        }
        if(claimantRequestDto.getMaritalStatusId() == null || claimantRequestDto.getMaritalStatusId().describeConstable().isEmpty()) {
            errors.add("The marital status field is required!");
        }
        if(!errors.contains("The marital status field is required!") && !maritalStatusRepository.existsById(claimantRequestDto.getMaritalStatusId())){
            errors.add("No marital status found with the given id!");
        }
        if(claimantRequestDto.getNeighborhood() == null ||claimantRequestDto.getNeighborhood().isBlank()){
            errors.add("The neighborhood field is required!");
        }
        if(claimantRequestDto.getNeighborhood().length() > 50){
            errors.add("The neighborhood can have a maximum of 50 characters");
        }
        if(claimantRequestDto.getCep() == null ||claimantRequestDto.getCep().isBlank()) {
            errors.add("The CEP field is required!");
        }
        var cepFormatted = inputCleaner.cleanseNumericInput(claimantRequestDto.getCep());
        if(cepFormatted.length() > 10){
            errors.add("The CEP can have a maximum of 10 characters");
        }
        if(claimantRequestDto.getBank().length() > 50){
            errors.add("The Bank can have a maximum of 50 characters");
        }
        var agencyFormatted = inputCleaner.cleanseNumericInput(claimantRequestDto.getAgency());
        var operationFormatedd = inputCleaner.cleanseNumericInput(claimantRequestDto.getOperation());
        var accountFormatted = inputCleaner.cleanseNumericInput(claimantRequestDto.getAccount());
        if(agencyFormatted.length() > 10){
            errors.add("The Agency can have a maximum of 10 characters");
        }
        if(operationFormatedd.length() > 10){
            errors.add("The Operation can have a maximum of 10 characters");
        }
        if(accountFormatted.length() > 20){
            errors.add("The Account can have a maximum of 20 characters");
        }
        String bank = claimantRequestDto.getBank();
        boolean contaBancoCheck = claimantRepository.existsByBankAndAgencyAndAccount(bank,
                agencyFormatted, accountFormatted);
        if(contaBancoCheck) {
            Long contaId = claimantRepository.findByBankAndAgencyAndAccountEquals(bank,
                    agencyFormatted, accountFormatted).getClaimantId();
            if (contaId != id) {
                errors.add("This bank account already registered!");
            }
        }
        if(claimantRequestDto.getContact() == null ||claimantRequestDto.getContact().isBlank()) {
            errors.add("The contact field is required!");
        }
        if(claimantRequestDto.getContact().length() > 255){
            errors.add("The Contact can have a maximum of 255 characters");
        }
        if(claimantRequestDto.getEmail().length() > 150){
            errors.add("The E-mail can have a maximum of 150 characters");
        }
        return errors;
    }

}
