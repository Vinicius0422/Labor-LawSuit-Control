package br.com.ViniciusGuedes.LaborLawsuitControl.controllers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations.DefendantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/defendant")
public class DefendantController {

    @Autowired
    private  DefendantServiceImpl defendantService;

    @GetMapping
    public ResponseEntity getAllDefendants(){
        var result = defendantService.getAllDefendants();
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @GetMapping("{id}")
    public ResponseEntity getDefendantById(@PathVariable(value = "id") Long id){
        var result = defendantService.getDefendantById(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @GetMapping("/searchByName")
    public ResponseEntity getDefendantsByName(@RequestParam String defendantName){
        var result = defendantService.getDefendantByName(defendantName);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @GetMapping("/searchByCpfOrCnpj")
    public ResponseEntity getDefendantByCpfOrCnpj(@RequestParam String cpfCnpj){
        var result = defendantService.getDefendantByCpfOrCnpj(cpfCnpj);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PostMapping
    public ResponseEntity saveDefendant(@RequestBody DefendantRequestDto defendantRequestDto){
        var request = defendantService.saveDefendant(defendantRequestDto);
        return ResponseEntity.status(request.getStatusCode()).body(request.getMessage());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateDefendant(@PathVariable(value = "id") Long id, @RequestBody DefendantRequestDto defendantRequestDto){
        var request = defendantService.updateDefendant(id, defendantRequestDto);
        return ResponseEntity.status(request.getStatusCode()).body(request.getMessage());
    }
}
