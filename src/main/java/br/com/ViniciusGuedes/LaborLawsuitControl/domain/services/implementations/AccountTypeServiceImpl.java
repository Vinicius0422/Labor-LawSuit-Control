package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.AccountTypeService;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.AccountTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    private AccountTypeRepository accountTypeRepository;

    public AccountTypeServiceImpl(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDefault getAllAccountsType() {
        var accountsType = accountTypeRepository.findAllAccountsType();
        if(accountsType.isEmpty()){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No records found!", accountsType);
        }
        return new ResponseDefault(HttpStatus.OK, "Search carried out!", accountsType);
    }
}
