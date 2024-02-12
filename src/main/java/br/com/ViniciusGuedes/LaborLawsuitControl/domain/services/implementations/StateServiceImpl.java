package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.state.StateResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.StateService;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StateServiceImpl implements StateService {

    private StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDefault getAllStates() {
        List<StateResponseDto> states = stateRepository.findAllStates();
        if(states.isEmpty()){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No records found!", states);
        }
        return new ResponseDefault<>(HttpStatus.OK, "Search carried out!", states);
    }
}
