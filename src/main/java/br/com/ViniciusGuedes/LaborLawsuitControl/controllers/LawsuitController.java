package br.com.ViniciusGuedes.LaborLawsuitControl.controllers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.LawsuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lawsuit")
public class LawsuitController {

    @Autowired
    private LawsuitService lawsuitService;

    @GetMapping
    public ResponseEntity findAllLawsuits() {
        try {
            return ResponseEntity.ok(lawsuitService.getAllLawsuits());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{lawsuitId}")
    public ResponseEntity findLawsuitById(@PathVariable(value = "lawsuitId") Long lawsuitId){
        try{
            return ResponseEntity.ok(lawsuitService.getLawsuitById(lawsuitId));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/searchByLawsuitNumber")
    public ResponseEntity findLawsuitByNumber(@RequestParam String lawsuitNumber){
        try{
            return ResponseEntity.ok(lawsuitService.getLawsuitByNumber(lawsuitNumber));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/searchLawsuitByClaimantName")
    public ResponseEntity findLawsuitByClaimantName(@RequestParam String claimantName){
        try{
            return ResponseEntity.ok(lawsuitService.getLawsuitByClaimantName(claimantName));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity saveLawsuit(@RequestBody LawsuitRequestDto lawsuitRequestDto){
        try{
            var lawsuitResponse = lawsuitService.saveLawsuit(lawsuitRequestDto);
            return ResponseEntity.status(lawsuitResponse.getStatusCode()).body(lawsuitResponse.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateLawsuit(@PathVariable(value = "id") Long id, @RequestBody LawsuitRequestDto lawsuitRequestDto){
        try{
            var lawsuitResponse = lawsuitService.updateLawsuit(id, lawsuitRequestDto);
            return ResponseEntity.status(lawsuitResponse.getStatusCode()).body(lawsuitResponse.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
