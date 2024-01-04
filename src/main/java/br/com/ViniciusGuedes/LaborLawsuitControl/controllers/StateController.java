package br.com.ViniciusGuedes.LaborLawsuitControl.controllers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations.StateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/state")
public class StateController {

    @Autowired
    private StateServiceImpl stateService;

    @GetMapping
    public ResponseEntity getAllStates(){
        var request = stateService.getAllStates();
        return ResponseEntity.status(request.getStatusCode()).body(request);
    }
}
