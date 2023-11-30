package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.SaveOrUpdateResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantServiceResponse;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.OnlyClaimantResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClaimantService {

    ClaimantServiceResponse<List<OnlyClaimantResponseDto>> getAllClaimants();

    ClaimantServiceResponse getClaimantById(Long claimantId);

    ClaimantServiceResponse getByCpf(String cpf);

    ClaimantServiceResponse<List<ClaimantResponseDto>> getByName(String claimantName);

    SaveOrUpdateResponseDefault saveClaimant(ClaimantRequestDto claimantRequestDto);

    SaveOrUpdateResponseDefault updateClaimant(Long id, ClaimantRequestDto claimantRequestDto);
}
