package br.com.ViniciusGuedes.LaborLawsuitControl.controllers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.LawsuitPhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lawsuitphase")
public class LawsuitPhaseController {

    @Autowired
    private LawsuitPhaseService lawsuitPhaseService;

    @GetMapping
    public ResponseEntity findAllLawsuitPhases(){
        try{
            return ResponseEntity.ok(lawsuitPhaseService.getAllLawsuitPhases());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
