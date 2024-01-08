package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.SaveOrUpdateResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface ClaimantService {

    ResponseDefault getAllClaimants(int page, int size);

    ResponseDefault getClaimantById(Long claimantId);

    ResponseDefault getByCpf(String cpf);

    ResponseDefault getByName(String claimantName);

    SaveOrUpdateResponseDefault saveClaimant(ClaimantRequestDto claimantRequestDto);

    SaveOrUpdateResponseDefault updateClaimant(Long id, ClaimantRequestDto claimantRequestDto);
}
