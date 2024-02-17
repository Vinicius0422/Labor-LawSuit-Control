package br.com.ViniciusGuedes.LaborLawsuitControl.mappers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.*;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.utils.InputCleaner;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.validations.DateFormatValidator;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ClaimantMapper {

    private NationalityRepository nationalityRepository;
    private MaritalStatusRepository maritalStatusRepository;
    private AccountTypeRepository accountTypeRepository;
    private StateRepository stateRepository;
    private CityRepository cityRepository;
    private InputCleaner inputCleaner;
    private DateFormatValidator dateFormatValidator;

    public ClaimantMapper(NationalityRepository nationalityRepository,
                          MaritalStatusRepository maritalStatusRepository,
                          AccountTypeRepository accountTypeRepository,
                          StateRepository stateRepository,
                          CityRepository cityRepository,
                          InputCleaner inputCleaner,
                          DateFormatValidator dateFormatValidator) {
        this.nationalityRepository = nationalityRepository;
        this.maritalStatusRepository = maritalStatusRepository;
        this.accountTypeRepository = accountTypeRepository;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.inputCleaner = inputCleaner;
        this.dateFormatValidator = dateFormatValidator;
    }

    public Claimant createClaimantFromDto(ClaimantRequestDto claimantRequestDto){
        Nationality nationality = nationalityRepository.findById(claimantRequestDto.nationalityId()).orElse(null);
        MaritalStatus maritalStatus = maritalStatusRepository.findById(claimantRequestDto.maritalStatusId()).orElse(null);
        AccountType accountType = accountTypeRepository.findById(claimantRequestDto.accountTypeId()).orElse(null);
        State state = stateRepository.findById(claimantRequestDto.stateId()).orElse(null);
        City city = cityRepository.findById(claimantRequestDto.cityId()).orElse(null);

        Claimant claimant = new Claimant(
                inputCleaner.removeAccents(claimantRequestDto.claimantName()),
                dateFormatValidator.parseLocalDate(claimantRequestDto.birthDate()),
                claimantRequestDto.occupation(),
                claimantRequestDto.cpf().substring(0,7),
                claimantRequestDto.cpf().substring(8,11),
                inputCleaner.cleanseNumericInput(claimantRequestDto.rg()),
                claimantRequestDto.orgaoRg(),
                inputCleaner.cleanseNumericInput(claimantRequestDto.cpf()),
                claimantRequestDto.pis(),
                claimantRequestDto.address(),
                claimantRequestDto.neighborhood(),
                city.getUf(),
                inputCleaner.cleanseNumericInput(claimantRequestDto.cep()),
                claimantRequestDto.bank(),
                inputCleaner.cleanseNumericInput(claimantRequestDto.agency()),
                claimantRequestDto.operation(),
                inputCleaner.cleanseNumericInput(claimantRequestDto.account()),
                claimantRequestDto.contact(),
                claimantRequestDto.email(),
                LocalDateTime.now(),
                null,
                nationality,
                maritalStatus,
                accountType,
                state,
                city);

        return claimant;
    }

    public void updateClaimantFields(Claimant existingClaimant, ClaimantRequestDto claimantRequestDto){
        Nationality nationality = nationalityRepository.findById(claimantRequestDto.nationalityId()).orElse(null);
        MaritalStatus maritalStatus = maritalStatusRepository.findById(claimantRequestDto.maritalStatusId()).orElse(null);
        AccountType accountType = accountTypeRepository.findById(claimantRequestDto.accountTypeId()).orElse(null);
        State state = stateRepository.findById(claimantRequestDto.stateId()).orElse(null);
        City city = cityRepository.findById(claimantRequestDto.cityId()).orElse(null);
        existingClaimant.setClaimantName(claimantRequestDto.claimantName());
        existingClaimant.setBirthDate(dateFormatValidator.parseLocalDate(claimantRequestDto.birthDate()));
        existingClaimant.setOccupation(claimantRequestDto.occupation());
        existingClaimant.setCtps(claimantRequestDto.cpf().substring(0,7));
        existingClaimant.setSerieCtps(claimantRequestDto.cpf().substring(7,11));
        existingClaimant.setRg(claimantRequestDto.rg());
        existingClaimant.setOrgaoRg(claimantRequestDto.orgaoRg());
        existingClaimant.setCpf(claimantRequestDto.cpf());
        existingClaimant.setPis(claimantRequestDto.pis());
        existingClaimant.setAddress(claimantRequestDto.address());
        existingClaimant.setNeighborhood(claimantRequestDto.neighborhood());
        existingClaimant.setCep(claimantRequestDto.cep());
        existingClaimant.setBank(claimantRequestDto.bank());
        existingClaimant.setAgency(claimantRequestDto.agency());
        existingClaimant.setOperation(claimantRequestDto.operation());
        existingClaimant.setAccount(claimantRequestDto.account());
        existingClaimant.setContact(claimantRequestDto.contact());
        existingClaimant.setEmail(claimantRequestDto.email());
        // Setar outras propriedades conforme necessário
        existingClaimant.setNationality(nationality);
        existingClaimant.setMaritalStatus(maritalStatus);
        existingClaimant.setAccountType(accountType);
        existingClaimant.setState(state);
        existingClaimant.setCity(city);
        existingClaimant.setUf(city.getUf());
    }

}
