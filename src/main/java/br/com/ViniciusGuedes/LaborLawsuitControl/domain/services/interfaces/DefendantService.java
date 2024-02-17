package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.SaveOrUpdateResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantSomeFieldsResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface DefendantService {

    Page<DefendantSomeFieldsResponseDto> getAllDefendants(int page, int size);

    DefendantResponseDto getDefendantById(Long defendantId);

    DefendantResponseDto getDefendantByCpfOrCnpj(String cpfCnpj);

    Page<DefendantResponseDto> getDefendantByName(String defendantName, int page, int size);

    SaveOrUpdateResponseDefault saveDefendant(DefendantRequestDto defendantRequestDto);

    SaveOrUpdateResponseDefault updateDefendant(Long id, DefendantRequestDto defendantRequestDto);

}
