package br.com.ViniciusGuedes.LaborLawsuitControl.controllers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.ClaimantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/claimant")
public class ClaimantController {

    @Autowired
    private ClaimantService claimantService;

    @GetMapping
    public ResponseEntity findAllClaimants(){
        try{
            return ResponseEntity.ok(claimantService.getAllClaimants());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{claimantId}")
    public ResponseEntity findClaimantById(@PathVariable(value = "claimantId") Long claimantId){
        try{
            return ResponseEntity.ok(claimantService.getClaimantById(claimantId));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/searchByCpf")
    public ResponseEntity findClaimantByCpf(@RequestParam String cpf){
        try{
            return ResponseEntity.ok(claimantService.getByCpf(cpf));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("/searchByName")
    public ResponseEntity findClaimantByName(@RequestParam String claimantName){
        try{
            return ResponseEntity.ok(claimantService.getByName(claimantName));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity saveClaimant(@RequestBody ClaimantRequestDto claimantRequestDto){
        try{
            var claimantResponse = claimantService.saveClaimant(claimantRequestDto);
            return ResponseEntity.status(claimantResponse.getStatusCode()).body(claimantResponse.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateClaimant(@PathVariable(value = "id") Long id, @RequestBody ClaimantRequestDto claimantRequestDto){
        try{
            var claimantResponse = claimantService.updateClaimant(id, claimantRequestDto);
            return ResponseEntity.status(claimantResponse.getStatusCode()).body(claimantResponse.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
