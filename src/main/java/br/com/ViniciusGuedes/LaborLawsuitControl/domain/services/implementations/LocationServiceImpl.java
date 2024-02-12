package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.location.LocationResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.LocationService;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDefault<List<LocationResponseDto>> getAllLocations() {
        var locations = locationRepository.findAllLocations();
        if(locations.isEmpty()){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No records found!", locations);
        }
        return new ResponseDefault<>(HttpStatus.OK, "Search carried out!", locations);
    }
}
