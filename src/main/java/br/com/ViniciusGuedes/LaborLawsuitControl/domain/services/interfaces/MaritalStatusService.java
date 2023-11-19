package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.maritalStatus.MaritalStatusResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MaritalStatusService {

    List<MaritalStatusResponseDto> getAllMaritalStatus();
}
