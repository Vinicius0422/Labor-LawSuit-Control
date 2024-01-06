package br.com.ViniciusGuedes.LaborLawsuitControl.controllers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress.ProgressRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/progress")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    @PostMapping
    public ResponseEntity saveProgress(@RequestBody ProgressRequestDto progressRequestDto){
        try{
            var progressResponse = progressService.saveProgress(progressRequestDto);
            return ResponseEntity.status(progressResponse.getStatusCode()).body(progressResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProgress(@PathVariable(value = "id") Long id, @RequestBody ProgressRequestDto progressRequestDto){
        try{
            var progressResponse = progressService.updateProgress(id, progressRequestDto);
            return ResponseEntity.status(progressResponse.getStatusCode()).body(progressResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
