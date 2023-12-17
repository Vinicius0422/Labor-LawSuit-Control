package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.SaveOrUpdateResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface LawsuitService {

    ResponseDefault getAllLawsuits();
    ResponseDefault getLawsuitById(Long lawsuitId);
    ResponseDefault getLawsuitByNumber(String lawsuitNumber);
    ResponseDefault getLawsuitByClaimantName(String claimantName);
    SaveOrUpdateResponseDefault saveLawsuit(LawsuitRequestDto lawsuitRequestDto);
    SaveOrUpdateResponseDefault updateLawsuit(Long id, LawsuitRequestDto lawsuitRequestDto);

}
