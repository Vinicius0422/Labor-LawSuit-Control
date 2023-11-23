package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress.ProgressRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface ProgressService {

    ResponseDefault saveProgress(ProgressRequestDto progressRequestDto);

    ResponseDefault updateProgress(Long id, ProgressRequestDto progressRequestDto);
}
