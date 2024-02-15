package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.SaveOrUpdateResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.OnlyClaimantResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface ClaimantService {

    @Transactional(readOnly = true)
    Page<OnlyClaimantResponseDto> getAllClaimants(int page, int size);

    ClaimantResponseDto getClaimantById(Long claimantId);

    ClaimantResponseDto getByCpf(String cpf);

    Page<ClaimantResponseDto> getByName(String claimantName, int page, int size);

    SaveOrUpdateResponseDefault saveClaimant(ClaimantRequestDto claimantRequestDto);

    SaveOrUpdateResponseDefault updateClaimant(Long id, ClaimantRequestDto claimantRequestDto);
}
