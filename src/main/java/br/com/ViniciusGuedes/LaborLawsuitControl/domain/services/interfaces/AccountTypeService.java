package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.accountType.AccountTypeResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountTypeService {

    List<AccountTypeResponseDto> getAllAccountsType();
}
