package br.com.ViniciusGuedes.LaborLawsuitControl.domain.validations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.utils.InputCleaner;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LawsuitValidator {

    @Autowired
    private LawsuitRepository lawsuitRepository;
    @Autowired
    private LawsuitPhaseRepository lawsuitPhaseRepository;
    @Autowired
    private LawsuitStatusRepository lawsuitStatusRepository;
    @Autowired
    private ClaimantRepository claimantRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private DefendantRepository defendantRepository;
    @Autowired
    private AttorneyRepository attorneyRepository;
    @Autowired
    private InputCleaner inputCleaner;
    @Autowired
    private DateFormatValidator dateFormatValidator;

    public List<String> saveLawsuitValidation(LawsuitRequestDto lawsuitRequestDto){
        List errors = new ArrayList<>();
        var lawsuitNumberFormatted = inputCleaner.cleanseNumericInput(lawsuitRequestDto.getLawsuitNumber());
        if(lawsuitNumberFormatted == null || lawsuitNumberFormatted.isBlank()){
            errors.add("The lawsuit number is required!");
        }
        if(lawsuitNumberFormatted.length() > 22){
            errors.add("The lawsuit number must have a maximum of 22 characters!");
        }
        if(lawsuitRepository.existsByLawsuitNumber(inputCleaner.cleanseNumericInput(lawsuitRequestDto.getLawsuitNumber()))){
            errors.add("This lawsuit number is already registered!");
        }
        lawsuitRequestDto.setLawsuitNumber(lawsuitNumberFormatted);
        if(lawsuitRequestDto.getDistributionDate() == null || lawsuitRequestDto.getDistributionDate().isBlank()){
            errors.add("The distribution date is required!");
        }
        if(!dateFormatValidator.isValidDate(lawsuitRequestDto.getDistributionDate())) {
            errors.add("Invalid distribution date format! Use the format dd/MM/yyyy.");
        }
        if (lawsuitRequestDto.getCpfCnpjDefendants() == null || lawsuitRequestDto.getCpfCnpjDefendants().isEmpty()) {
            errors.add("The field CPF/CNPJ Defendant is required!");
        }
        if(!lawsuitPhaseRepository.existsByLawsuitPhaseId(lawsuitRequestDto.getLawsuitPhaseId())){
            errors.add("No lawsuit phase found with the given id!");
        }
        if(!lawsuitStatusRepository.existsByLawsuitStatusId(lawsuitRequestDto.getLawsuitStatusId())){
            errors.add("No lawsuit status found with the given id!");
        }
        if(!locationRepository.existsByLocationId(lawsuitRequestDto.getLocationId())){
            errors.add("No location found with the given id!");
        }
        var claimantFormatted = inputCleaner.cleanseNumericInput(lawsuitRequestDto.getCpfClaimant());
        if(claimantFormatted == null || claimantFormatted.isBlank()){
            errors.add("Inform the claimant's CPF!");
        }
        if(!claimantRepository.existsByCpf(claimantFormatted)){
            errors.add("CPF: " + lawsuitRequestDto.getCpfClaimant() + " not registered for any claimant");
        }
        if(lawsuitRequestDto.getCpfCnpjDefendants().isEmpty()){
            errors.add("Inform the defendant's CPF/CNPJ!");
        }
        if(lawsuitRequestDto.getCpfCnpjDefendants() != null && !lawsuitRequestDto.getCpfCnpjDefendants().isEmpty()){
            for (int i = 0; i < lawsuitRequestDto.getCpfCnpjDefendants().size(); i++) {
                if(!defendantRepository.existsByCpfCnpj(inputCleaner.cleanseNumericInput(lawsuitRequestDto.getCpfCnpjDefendants().get(i)))){
                    errors.add("CPF/CNPJ: "+ lawsuitRequestDto.getCpfCnpjDefendants().get(i) + " not registered for any defendant");
                }
            }
        }
        if(lawsuitRequestDto.getAttorneys().isEmpty()){
            errors.add("Select at least one attorney!");
        }
        if(lawsuitRequestDto.getAttorneys() != null && !lawsuitRequestDto.getAttorneys().isEmpty()){
            for (int i = 0; i < lawsuitRequestDto.getAttorneys().size(); i++) {
                if(!attorneyRepository.existsById(lawsuitRequestDto.getAttorneys().get(i))){
                    errors.add("No attorney found with the given id!");
                }
            }
        }
        return errors;
    }

    public List<String> updateLawsuitValidation(Long id, LawsuitRequestDto lawsuitRequestDto){
        List errors = new ArrayList<>();
        var lawsuitNumberFormatted = inputCleaner.cleanseNumericInput(lawsuitRequestDto.getLawsuitNumber());
        var lawsuitNumberExists = lawsuitRepository.existsByLawsuitNumber(lawsuitNumberFormatted);
        if(lawsuitNumberFormatted == null || lawsuitNumberFormatted.isBlank()){
            errors.add("The lawsuit number is required!");
        }
        if(lawsuitNumberFormatted.length() > 22){
            errors.add("The lawsuit number must have a maximum of 22 characters!");
        }
        if(lawsuitNumberExists){
            Long lawsuitExistentId = lawsuitRepository.findByLawsuitNumberEquals(lawsuitNumberFormatted).getLawsuitId();
            if(id != lawsuitExistentId){
                errors.add("This Lawsuit Number is already registered!");
            }
        }
        if(lawsuitRepository.existsByLawsuitNumber(inputCleaner.cleanseNumericInput(lawsuitRequestDto.getLawsuitNumber()))){
            errors.add("This lawsuit number is already registered!");
        }
        lawsuitRequestDto.setLawsuitNumber(lawsuitNumberFormatted);
        if(lawsuitRequestDto.getDistributionDate() == null || lawsuitRequestDto.getDistributionDate().isBlank()){
            errors.add("The distribution date is required!");
        }
        if(!dateFormatValidator.isValidDate(lawsuitRequestDto.getDistributionDate())) {
            errors.add("Invalid distribution date format! Use the format dd/MM/yyyy.");
        }
        if(lawsuitRequestDto.getCpfCnpjDefendants() == null || lawsuitRequestDto.getCpfCnpjDefendants().isEmpty()) {
            errors.add("The field CPF/CNPJ Defendant is required!");
        }
        if(!lawsuitPhaseRepository.existsByLawsuitPhaseId(lawsuitRequestDto.getLawsuitPhaseId())){
            errors.add("No lawsuit phase found with the given id!");
        }
        if(!lawsuitStatusRepository.existsByLawsuitStatusId(lawsuitRequestDto.getLawsuitStatusId())){
            errors.add("No lawsuit status found with the given id!");
        }
        if(!locationRepository.existsByLocationId(lawsuitRequestDto.getLawsuitStatusId())){
            errors.add("No location found with the given id!");
        }
        var claimantFormatted = inputCleaner.cleanseNumericInput(lawsuitRequestDto.getCpfClaimant());
        if(claimantFormatted == null || claimantFormatted.isBlank()){
            errors.add("Inform the claimant's CPF!");
        }
        if(!claimantRepository.existsByCpf(claimantFormatted)){
            errors.add("CPF: " + lawsuitRequestDto.getCpfClaimant() + " not registered for any claimant");
        }
        if(lawsuitRequestDto.getCpfCnpjDefendants().isEmpty()){
            errors.add("Inform the defendant's CPF/CNPJ!");
        }
        if(lawsuitRequestDto.getCpfCnpjDefendants() != null && !lawsuitRequestDto.getCpfCnpjDefendants().isEmpty()){
            for (int i = 0; i < lawsuitRequestDto.getCpfCnpjDefendants().size(); i++) {
                if(!defendantRepository.existsByCpfCnpj(inputCleaner.cleanseNumericInput(lawsuitRequestDto.getCpfCnpjDefendants().get(i)))){
                    errors.add("CPF/CNPJ: "+ lawsuitRequestDto.getCpfCnpjDefendants().get(i) + " not registered for any defendant");
                }
            }
        }
        if(lawsuitRequestDto.getAttorneys().isEmpty()){
            errors.add("Select at least one attorney!");
        }
        if(lawsuitRequestDto.getAttorneys() != null && !lawsuitRequestDto.getAttorneys().isEmpty()){
            for (int i = 0; i < lawsuitRequestDto.getAttorneys().size(); i++) {
                if(!attorneyRepository.existsById(lawsuitRequestDto.getAttorneys().get(i))){
                    errors.add("No attorney found with the given id!");
                }
            }
        }
        return errors;
    }
}
