package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuitStatus.LawsuitStatusResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LawsuitStatusService {

    List<LawsuitStatusResponseDto> getAllLawsuitStatus();
}
