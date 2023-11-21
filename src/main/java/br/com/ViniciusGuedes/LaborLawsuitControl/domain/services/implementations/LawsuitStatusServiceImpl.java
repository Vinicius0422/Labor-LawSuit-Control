package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuitStatus.LawsuitStatusResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.LawsuitStatusService;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.LawsuitStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LawsuitStatusServiceImpl implements LawsuitStatusService {

    @Autowired
    private LawsuitStatusRepository lawsuitStatusRepository;

    @Override
    public List<LawsuitStatusResponseDto> getAllLawsuitStatus() {
        return lawsuitStatusRepository.findAllLawsuitStatus();
    }
}
