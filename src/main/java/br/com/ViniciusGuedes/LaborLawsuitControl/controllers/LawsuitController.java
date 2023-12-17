package br.com.ViniciusGuedes.LaborLawsuitControl.controllers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations.LawsuitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lawsuit")
public class LawsuitController {

    @Autowired
    private LawsuitServiceImpl lawsuitService;

    @GetMapping
    public ResponseEntity getAllLawsuits(){
        return ResponseEntity.ok(lawsuitService.getAllLawsuits());
    }

    @GetMapping("/{lawsuitId}")
    public ResponseEntity getLawsuitById(@PathVariable(value = "lawsuitId") Long lawsuitId){
        return ResponseEntity.ok(lawsuitService.getLawsuitById(lawsuitId));
    }

    @GetMapping("/searchByLawsuitNumber")
    public ResponseEntity getLawsuitByNumber(@RequestParam String lawsuitNumber){
        var request = lawsuitService.getLawsuitByNumber(lawsuitNumber);
        return ResponseEntity.status(request.getStatusCode()).body(request);
    }

    @GetMapping("/searchLawsuitByClaimantName")
    public ResponseEntity getLawsuitByClaimantName(@RequestParam String claimantName){
        var request = lawsuitService.getLawsuitByClaimantName(claimantName);
        return ResponseEntity.status(request.getStatusCode()).body(request);
    }

    @PostMapping
    public ResponseEntity saveLawsuit(@RequestBody LawsuitRequestDto lawsuitRequestDto){
        var request = lawsuitService.saveLawsuit(lawsuitRequestDto);
        return ResponseEntity.status(request.getStatusCode()).body(request.getMessage());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateLawsuit(@PathVariable(value = "id") Long id, @RequestBody LawsuitRequestDto lawsuitRequestDto){
        var request = lawsuitService.updateLawsuit(id, lawsuitRequestDto);
        return ResponseEntity.status(request.getStatusCode()).body(request.getMessage());
    }
}
