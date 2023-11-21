package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.nationality.NationalityResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.NationalityService;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.NationalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationalityServiceImpl implements NationalityService {

    @Autowired
    private NationalityRepository nationalityRepository;

    @Override
    public List<NationalityResponseDto> getAllNationalities() {
        return nationalityRepository.findAllNationalities();
    }
}
