package br.com.ViniciusGuedes.LaborLawsuitControl.controllers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityServiceImpl cityService;

    @GetMapping(path = "/{stateId}")
    public ResponseEntity findCitiesByStateId(@PathVariable("stateId") Long stateId){
        var request = cityService.getCitiesByStateId(stateId);
        return ResponseEntity.status(request.getStatusCode()).body(request);
    }
}
