package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.SaveOrUpdateResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface DefendantService {

    ResponseDefault getAllDefendants();

    ResponseDefault getDefendantByName(String defendantName);

    ResponseDefault getDefendantById(Long defendantId);

    ResponseDefault getDefendantByCpfOrCnpj(String cpfCnpj);

    SaveOrUpdateResponseDefault saveDefendant(DefendantRequestDto defendantRequestDto);

    SaveOrUpdateResponseDefault updateDefendant(Long id, DefendantRequestDto defendantRequestDto);

}
