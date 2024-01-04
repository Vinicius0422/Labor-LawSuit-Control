package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import org.springframework.stereotype.Service;

@Service
public interface StateService {

    ResponseDefault getAllStates();
}
