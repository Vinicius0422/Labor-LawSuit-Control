package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.maritalStatus.MaritalStatusResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.MaritalStatusService;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.MaritalStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MaritalStatusServiceImpl implements MaritalStatusService {

    @Autowired
    private MaritalStatusRepository maritalStatusRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseDefault<List<MaritalStatusResponseDto>> getAllMaritalStatus() {
        var maritalStatus = maritalStatusRepository.findAllMaritalStatus();
        if(maritalStatus.isEmpty()){
            return new ResponseDefault(HttpStatus.OK, "No records found!", maritalStatus);
        }
        return new ResponseDefault<>(HttpStatus.OK, "Search carried out!", maritalStatus);
    }
}
