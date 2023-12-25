package br.com.ViniciusGuedes.LaborLawsuitControl.controllers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress.ProgressRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations.ProgressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/progress")
public class ProgressController {

    @Autowired
    private ProgressServiceImpl progressService;

    @PostMapping
    public ResponseEntity saveProgress(@RequestBody ProgressRequestDto progressRequestDto){
        var request = progressService.saveProgress(progressRequestDto);
        return ResponseEntity.status(request.getStatusCode()).body(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProgress(@PathVariable(value = "id") Long id, @RequestBody ProgressRequestDto progressRequestDto){
        var request = progressService.updateProgress(id, progressRequestDto);
        return ResponseEntity.status(request.getStatusCode()).body(request);
    }
}
