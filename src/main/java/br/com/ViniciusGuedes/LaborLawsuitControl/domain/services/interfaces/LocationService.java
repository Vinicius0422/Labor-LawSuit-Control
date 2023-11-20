package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.location.LocationResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationService {

    List<LocationResponseDto> getAllLocations();
}
