package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuitPhase.LawsuitPhaseResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuitStatus.LawsuitStatusResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.LawsuitPhaseService;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.LawsuitPhaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class LawsuitPhaseServiceImpl implements LawsuitPhaseService {

    @Autowired
    private LawsuitPhaseRepository lawsuitPhaseRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseDefault<List<LawsuitPhaseResponseDto>> getAllLawsuitPhases() {
        var lawsuitPhases = lawsuitPhaseRepository.findAllLawsuitPhases();
        if(lawsuitPhases.isEmpty()){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No records found!", lawsuitPhases);
        }
        return new ResponseDefault<>(HttpStatus.OK, "Search carried out!", lawsuitPhases);
    }
}
