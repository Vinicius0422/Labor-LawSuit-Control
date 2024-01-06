package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.nationality.NationalityResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NationalityService {

    ResponseDefault<List<NationalityResponseDto>> getAllNationalities();
}
