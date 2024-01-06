package br.com.ViniciusGuedes.LaborLawsuitControl.controllers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.LawsuitStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lawsuitstatus")
public class LawsuitStatusController {

    @Autowired
    private LawsuitStatusService lawsuitStatusService;

    @GetMapping
    public ResponseEntity findAllLawsuitStatus(){
        try{
            return ResponseEntity.ok(lawsuitStatusService.getAllLawsuitStatus());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
