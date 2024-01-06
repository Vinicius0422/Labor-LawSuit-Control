package br.com.ViniciusGuedes.LaborLawsuitControl.controllers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.DefendantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/defendant")
public class DefendantController {

    @Autowired
    private DefendantService defendantService;

    @GetMapping
    public ResponseEntity findAllDefendants(){
        try{
            return ResponseEntity.ok(defendantService.getAllDefendants());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity findDefendantById(@PathVariable(value = "id") Long id){
        try{
            return ResponseEntity.ok( defendantService.getDefendantById(id));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/searchByName")
    public ResponseEntity findDefendantsByName(@RequestParam String defendantName){
        try{
            return ResponseEntity.ok(defendantService.getDefendantByName(defendantName));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/searchByCpfOrCnpj")
    public ResponseEntity findDefendantByCpfOrCnpj(@RequestParam String cpfCnpj){
        try{
            return ResponseEntity.ok(defendantService.getDefendantByCpfOrCnpj(cpfCnpj));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity saveDefendant(@RequestBody DefendantRequestDto defendantRequestDto){
        try{
            var defendantResponse = defendantService.saveDefendant(defendantRequestDto);
            return ResponseEntity.status(defendantResponse.getStatusCode()).body(defendantResponse.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateDefendant(@PathVariable(value = "id") Long id, @RequestBody DefendantRequestDto defendantRequestDto){
        try{
            var defendantResponse = defendantService.updateDefendant(id, defendantRequestDto);
            return ResponseEntity.status(defendantResponse.getStatusCode()).body(defendantResponse.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
