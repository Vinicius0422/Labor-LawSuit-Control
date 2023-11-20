package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.location.LocationResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.LocationService;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationRepository locationRepository;

    @Override
    public List<LocationResponseDto> getAllLocations() {
        return locationRepository.findAllLocations();
    }
}
