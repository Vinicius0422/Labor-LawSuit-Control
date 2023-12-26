package br.com.ViniciusGuedes.LaborLawsuitControl.controllers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation.AnnotationRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations.AnnotationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/annotation")
public class AnnotationController {

    @Autowired
    private AnnotationServiceImpl annotationService;

    @PostMapping
    public ResponseEntity saveAnnotation(@RequestBody AnnotationRequestDto annotationRequestDto){
        var request = annotationService.saveAnnotation(annotationRequestDto);
        return ResponseEntity.status(request.getStatusCode()).body(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAnnotation(@PathVariable(value = "id") Long id, @RequestBody AnnotationRequestDto annotationRequestDto){
        var request = annotationService.updateAnnotation(id, annotationRequestDto);
        return ResponseEntity.status(request.getStatusCode()).body(request);
    }
}
