package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.maritalStatus.MaritalStatusResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.MaritalStatusService;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.MaritalStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaritalStatusServiceImpl implements MaritalStatusService {

    @Autowired
    private MaritalStatusRepository maritalStatusRepository;

    @Override
    public List<MaritalStatusResponseDto> getAllMaritalStatus() {
        return maritalStatusRepository.findAllMaritalStatus();
    }
}
