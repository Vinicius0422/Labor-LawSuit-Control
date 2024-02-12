package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuitStatus.LawsuitStatusResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.LawsuitStatusService;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.LawsuitStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LawsuitStatusServiceImpl implements LawsuitStatusService {

    private LawsuitStatusRepository lawsuitStatusRepository;

    public LawsuitStatusServiceImpl(LawsuitStatusRepository lawsuitStatusRepository) {
        this.lawsuitStatusRepository = lawsuitStatusRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDefault<List<LawsuitStatusResponseDto>> getAllLawsuitStatus() {
        var lawsuitStatus = lawsuitStatusRepository.findAllLawsuitStatus();
        if(lawsuitStatus.isEmpty()){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No records found!", lawsuitStatus);
        }
        return new ResponseDefault<>(HttpStatus.OK, "Search carried out!", lawsuitStatus);
    }
}
