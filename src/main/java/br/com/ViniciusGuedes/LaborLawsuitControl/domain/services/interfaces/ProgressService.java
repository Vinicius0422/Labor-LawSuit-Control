package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.SaveOrUpdateResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress.ProgressRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface ProgressService {

    SaveOrUpdateResponseDefault saveProgress(ProgressRequestDto progressRequestDto);

    SaveOrUpdateResponseDefault updateProgress(Long id, ProgressRequestDto progressRequestDto);
}
