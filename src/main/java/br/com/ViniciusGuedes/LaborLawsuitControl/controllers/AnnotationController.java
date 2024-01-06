package br.com.ViniciusGuedes.LaborLawsuitControl.controllers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation.AnnotationRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.AnnotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/annotation")
public class AnnotationController {

    @Autowired
    private AnnotationService annotationService;

    @PostMapping
    public ResponseEntity saveAnnotation(@RequestBody AnnotationRequestDto annotationRequestDto){
        try{
            var annotationResponse = annotationService.saveAnnotation(annotationRequestDto);
            return ResponseEntity.status(annotationResponse.getStatusCode()).body(annotationResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAnnotation(@PathVariable(value = "id") Long id, @RequestBody AnnotationRequestDto annotationRequestDto){
        try{
            var annotationResponse = annotationService.updateAnnotation(id, annotationRequestDto);
            return ResponseEntity.status(annotationResponse.getStatusCode()).body(annotationResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
