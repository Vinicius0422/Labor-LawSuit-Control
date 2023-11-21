package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuitPhase.LawsuitPhaseResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LawsuitPhaseService {

    List<LawsuitPhaseResponseDto> getAllLawsuitPhases();
}
