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

    private ClaimantRepository claimantRepository;
    private InputCleaner inputCleaner;
    private CityRepository cityRepository;
    private StateRepository stateRepository;
    private NationalityRepository nationalityRepository;
    private MaritalStatusRepository maritalStatusRepository;
    private AccountTypeRepository accountTypeRepository;
    private DateFormatValidator dateFormatValidator;

    public ClaimantValidator(ClaimantRepository claimantRepository, InputCleaner inputCleaner, CityRepository cityRepository,
                             StateRepository stateRepository, NationalityRepository nationalityRepository,
                             MaritalStatusRepository maritalStatusRepository, AccountTypeRepository accountTypeRepository,
                             DateFormatValidator dateFormatValidator) {
        this.claimantRepository = claimantRepository;
        this.inputCleaner = inputCleaner;
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
        this.nationalityRepository = nationalityRepository;
        this.maritalStatusRepository = maritalStatusRepository;
        this.accountTypeRepository = accountTypeRepository;
        this.dateFormatValidator = dateFormatValidator;
    }

    public List<String> validationSaveClaimantRequestDto(ClaimantRequestDto claimantRequestDto){
        List<String> errors = new ArrayList<>();
        if(claimantRequestDto.claimantName().length() < 3){
            errors.add("Enter a name with at least 3 characters!");
        }
        if(!dateFormatValidator.isValidDate(claimantRequestDto.birthDate())) {
            errors.add("Invalid birth date format! Use the format dd/MM/yyyy.");
        }
        var rgFormatted = inputCleaner.cleanseNumericInput(claimantRequestDto.rg());
        if(rgFormatted.length() != 7){
            errors.add("The rg must have 7 characters");
        }
        if(claimantRepository.existsByRg(rgFormatted)){
            errors.add("This RG already registered!");
        }
        var cpfFormatted = inputCleaner.cleanseNumericInput(claimantRequestDto.cpf());
        if(cpfFormatted.length() != 11){
            errors.add("The CPF must have 11 characters");
        }
        if (claimantRepository.existsByCpf(cpfFormatted)){
            errors.add("This CPF already registered!");
        }
        if( !stateRepository.existsById(claimantRequestDto.stateId())){
            errors.add("No state found with the given id!");
        }
        if(!cityRepository.existsById(claimantRequestDto.cityId())){
            errors.add("No city found with the given id!");
        }
        if(!accountTypeRepository.existsById(claimantRequestDto.accountTypeId())){
            errors.add("No account type found with the given id!");
        }
        if(!nationalityRepository.existsById(claimantRequestDto.nationalityId())){
            errors.add("No nationality found with the given id!");
        }
        if(!maritalStatusRepository.existsById(claimantRequestDto.maritalStatusId())){
            errors.add("No marital status found with the given id!");
        }
        var cepFormatted = inputCleaner.cleanseNumericInput(claimantRequestDto.cep());
        if(cepFormatted.length() != 8){
            errors.add("The CEP must have 8 characters");
        }
        var agencyFormatted = inputCleaner.cleanseNumericInput(claimantRequestDto.agency());
        var accountFormatted = inputCleaner.cleanseNumericInput(claimantRequestDto.account());
        String bank = claimantRequestDto.bank();
        if(claimantRepository.existsByBankAndAgencyAndAccount(bank, agencyFormatted, accountFormatted)){
            errors.add("This bank account already registered!");
        }
        return errors;
    }

    public List<String> validationUpdateClaimantRequestDto(Long id, ClaimantRequestDto claimantRequestDto){
        List<String> errors = new ArrayList<>();
        if(claimantRequestDto.claimantName().length() < 3){
            errors.add("Enter a name with at least 3 characters!");
        }
        if(!dateFormatValidator.isValidDate(claimantRequestDto.birthDate())) {
            errors.add("Invalid birth date format! Use the format dd/MM/yyyy.");
        }
        var rgFormatted = inputCleaner.cleanseNumericInput(claimantRequestDto.rg());
        if(rgFormatted.length() != 7){
            errors.add("The rg must have 7 characters");
        }
        boolean rgCheck = claimantRepository.existsByRg(rgFormatted);
        if(rgCheck) {
            Long rgId = claimantRepository.findByRgEquals(rgFormatted).getClaimantId();
            if (rgId != id) {
                errors.add("This RG already registered!");
            }
        }
        var cpfFormatted = inputCleaner.cleanseNumericInput(claimantRequestDto.cpf());
        if(cpfFormatted.length() != 11){
            errors.add("The CPF field must have 11 characters");
        }
        boolean cpfCheck = claimantRepository.existsByCpf(cpfFormatted);
        if(cpfCheck) {
            Long cpfId = claimantRepository.findByCpfEquals(cpfFormatted).getClaimantId();
            if (cpfId != id) {
                errors.add("This CPF already registered!");
            }
        }
        if(!stateRepository.existsById(claimantRequestDto.stateId())){
            errors.add("No state found with the given id!");
        }
        if(!cityRepository.existsById(claimantRequestDto.cityId())){
            errors.add("No city found with the given id!");
        }
        if(!accountTypeRepository.existsById(claimantRequestDto.accountTypeId())){
            errors.add("No account type found with the given id!");
        }
        if(!nationalityRepository.existsById(claimantRequestDto.nationalityId())){
            errors.add("No nationality found with the given id!");
        }
        if(!maritalStatusRepository.existsById(claimantRequestDto.maritalStatusId())){
            errors.add("No marital status found with the given id!");
        }
        var cepFormatted = inputCleaner.cleanseNumericInput(claimantRequestDto.cep());
        if(cepFormatted.length() != 8){
            errors.add("The CEP must have 8 characters");
        }
        var agencyFormatted = inputCleaner.cleanseNumericInput(claimantRequestDto.agency());
        var accountFormatted = inputCleaner.cleanseNumericInput(claimantRequestDto.account());
        String bank = claimantRequestDto.bank();
        boolean contaBancoCheck = claimantRepository.existsByBankAndAgencyAndAccount(bank,
                agencyFormatted, accountFormatted);
        if(contaBancoCheck) {
            Long contaId = claimantRepository.findByBankAndAgencyAndAccountEquals(bank,
                    agencyFormatted, accountFormatted).getClaimantId();
            if (contaId != id) {
                errors.add("This bank account already registered!");
            }
        }
        return errors;
    }
}
