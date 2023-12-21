package br.com.ViniciusGuedes.LaborLawsuitControl.controllers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations.ClaimantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/claimant")
public class ClaimantController {

    @Autowired
    private ClaimantServiceImpl claimantService;

    @GetMapping
    public ResponseEntity getAllClaimants(){
        return ResponseEntity.ok(claimantService.getAllClaimants());
    }

    @GetMapping("/{claimantId}")
    public ResponseEntity getClaimantById(@PathVariable(value = "claimantId") Long claimantId){
        var request = claimantService.getClaimantById(claimantId);
        return ResponseEntity.status(request.getStatusCode()).body(request);
    }

    @GetMapping("/searchByCpf")
    public ResponseEntity getClaimantByCpf(@RequestParam String cpf){
        var request = claimantService.getByCpf(cpf);
        return ResponseEntity.status(request.getStatusCode()).body(request);
    }

    @GetMapping("/searchByName")
    public ResponseEntity getClaimantByName(@RequestParam String claimantName){
        var request = claimantService.getByName(claimantName);
        return ResponseEntity.status(request.getStatusCode()).body(request);
    }

    @PostMapping
    public ResponseEntity saveClaimant(@RequestBody ClaimantRequestDto claimantRequestDto){
        var request = claimantService.saveClaimant(claimantRequestDto);
        return ResponseEntity.status(request.getStatusCode()).body(request.getMessage());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateClaimant(@PathVariable(value = "id") Long id, @RequestBody ClaimantRequestDto claimantRequestDto){
        var request = claimantService.updateClaimant(id, claimantRequestDto);
        return ResponseEntity.status(request.getStatusCode()).body(request.getMessage());
    }
}
