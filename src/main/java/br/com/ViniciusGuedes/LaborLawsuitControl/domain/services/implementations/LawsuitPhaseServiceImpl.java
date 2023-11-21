package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuitPhase.LawsuitPhaseResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.LawsuitPhaseService;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.LawsuitPhaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LawsuitPhaseServiceImpl implements LawsuitPhaseService {

    @Autowired
    private LawsuitPhaseRepository lawsuitPhaseRepository;

    @Override
    public List<LawsuitPhaseResponseDto> getAllLawsuitPhases() {
        return lawsuitPhaseRepository.findAllLawsuitPhases();
    }
}
