package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.accountType.AccountTypeResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.AccountTypeService;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseDefault getAllAccountsType() {
        var accountsType = accountTypeRepository.findAllAccountsType();
        if(accountsType.isEmpty()){
            return new ResponseDefault(HttpStatus.OK, "No records found!", accountsType);
        }
        return new ResponseDefault(HttpStatus.OK, "Search carried out!", accountsType);
    }
}
