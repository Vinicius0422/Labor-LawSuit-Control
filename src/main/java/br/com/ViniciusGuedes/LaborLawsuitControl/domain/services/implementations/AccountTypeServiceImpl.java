package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.accountType.AccountTypeResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.AccountTypeService;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Override
    public List<AccountTypeResponseDto> getAllAccountsType() {
        return accountTypeRepository.findAllAccountsType();
    }
}
