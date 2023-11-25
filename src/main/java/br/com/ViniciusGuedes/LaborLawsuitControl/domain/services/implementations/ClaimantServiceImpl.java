package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.OnlyClaimantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.ClaimantService;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.ClaimantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimantServiceImpl implements ClaimantService {

    @Autowired
    private ClaimantRepository claimantRepository;


    @Override
    public List<OnlyClaimantResponseDto> getAllClaimants() {
        return null;
    }

    @Override
    public ClaimantResponseDto getById(Long claimantId) {
        return claimantRepository.findClaimantById(claimantId);
    }

    @Override
    public ClaimantResponseDto getByCpf(String cpf) {
        return null;
    }

    @Override
    public List<ClaimantResponseDto> getByName(String name) {
        return null;
    }

    @Override
    public ResponseDefault saveClaimant(ClaimantRequestDto claimantRequestDto) {
        return null;
    }

    @Override
    public ResponseDefault updateClaimant(Long id, ClaimantRequestDto claimantRequestDto) {
        return null;
    }
}
