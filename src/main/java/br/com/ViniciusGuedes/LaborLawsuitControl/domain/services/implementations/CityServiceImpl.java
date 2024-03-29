package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.city.CityResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.CityService;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDefault getCitiesByStateId(Long stateId) {
        List<CityResponseDto> cities = cityRepository.findAllCitiesByStateId(stateId);
        if(cities.isEmpty()){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No cities found for this state", null);
        }
        return new ResponseDefault(HttpStatus.OK, "Search carried out!", cities);
    }
}
