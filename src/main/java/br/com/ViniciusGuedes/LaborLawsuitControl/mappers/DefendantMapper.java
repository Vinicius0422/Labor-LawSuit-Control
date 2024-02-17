package br.com.ViniciusGuedes.LaborLawsuitControl.mappers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.City;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Defendant;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.PersonType;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.State;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.utils.InputCleaner;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.CityRepository;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.StateRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DefendantMapper {

    private StateRepository stateRepository;
    private CityRepository cityRepository;
    private InputCleaner inputCleaner;

    public DefendantMapper(StateRepository stateRepository, CityRepository cityRepository, InputCleaner inputCleaner) {
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.inputCleaner = inputCleaner;
    }

    public Defendant defendantDtoToEntity(DefendantRequestDto defendantRequestDto){

        State state = stateRepository.findById(defendantRequestDto.stateId()).orElse(null);
        City city = cityRepository.findById(defendantRequestDto.cityId()).orElse(null);

        PersonType personType;
        if(defendantRequestDto.personType() == "FISICA"){
            personType = PersonType.FISICA;
        } else {
            personType = PersonType.JURIDICA;
        }

        Defendant defendant = new Defendant(
                defendantRequestDto.defendantName(),
                personType,
                inputCleaner.cleanseNumericInput(defendantRequestDto.cpfCnpj()),
                defendantRequestDto.address(),
                defendantRequestDto.neighborhood(),
                city.getUf(),
                inputCleaner.cleanseNumericInput(defendantRequestDto.cep()),
                defendantRequestDto.contact(),
                defendantRequestDto.email(),
                LocalDateTime.now(),
                null,
                state,
                city);

        return defendant;
    }

        public void updateDefendantFields(Defendant existingDefendant, DefendantRequestDto defendantRequestDto){
        State state = stateRepository.findById(defendantRequestDto.stateId()).orElse(null);
        City city = cityRepository.findById(defendantRequestDto.cityId()).orElse(null);

        existingDefendant.setDefendantName(inputCleaner.removeAccents(defendantRequestDto.defendantName()));
        PersonType personType;
        if(defendantRequestDto.personType() == "FISICA"){
            personType = PersonType.FISICA;
        } else {
            personType = PersonType.JURIDICA;
        }
        existingDefendant.setPersonType(personType);
        existingDefendant.setCpfCnpj(inputCleaner.cleanseNumericInput(defendantRequestDto.cpfCnpj()));
        existingDefendant.setAddress(defendantRequestDto.address());
        existingDefendant.setState(state);
        existingDefendant.setCity(city);
        existingDefendant.setNeighborhood(defendantRequestDto.neighborhood());
        existingDefendant.setUf(city.getUf());
        existingDefendant.setCep(inputCleaner.cleanseNumericInput(defendantRequestDto.cep()));
        existingDefendant.setContact(defendantRequestDto.contact());
        existingDefendant.setEmail(defendantRequestDto.email());
    }
}
