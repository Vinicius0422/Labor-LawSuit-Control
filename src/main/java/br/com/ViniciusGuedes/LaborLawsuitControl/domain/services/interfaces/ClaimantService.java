package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.OnlyClaimantResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClaimantService {

    List<OnlyClaimantResponseDto> getAllClaimants();

    ClaimantResponseDto getById(Long claimantId);

    ClaimantResponseDto getByCpf(String cpf);

    List<ClaimantResponseDto> getByName(String name);

    ResponseDefault saveClaimant(ClaimantRequestDto claimantRequestDto);

    ResponseDefault updateClaimant(Long id, ClaimantRequestDto claimantRequestDto);
}
