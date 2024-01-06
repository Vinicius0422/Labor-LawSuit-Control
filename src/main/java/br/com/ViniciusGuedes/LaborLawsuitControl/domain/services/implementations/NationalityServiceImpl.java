package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.nationality.NationalityResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.NationalityService;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.NationalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NationalityServiceImpl implements NationalityService {

    @Autowired
    private NationalityRepository nationalityRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseDefault<List<NationalityResponseDto>> getAllNationalities() {
        var nationalities = nationalityRepository.findAllNationalities();
        if(nationalities.isEmpty()){
            return new ResponseDefault(HttpStatus.OK, "No records found!", nationalities);
        }
        return new ResponseDefault<>(HttpStatus.OK, "Search carried out!", nationalities);
    }
}
