package br.com.ViniciusGuedes.LaborLawsuitControl.controllers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation.AnnotationRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.AnnotationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/annotation", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Annotation Controller", description = "API endpoints for managing annotations of labor lawsuits")
public class AnnotationController {

    private AnnotationService annotationService;

    public AnnotationController(AnnotationService annotationService) {
        this.annotationService = annotationService;
    }

    @PostMapping
    @Operation(summary = "Create a new annotation", description = "Create a new annotation based on the provided data", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Annotation created successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "422", description = "Annotation not created due validation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity saveAnnotation(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request body containing annotation data", required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) @RequestBody AnnotationRequestDto annotationRequestDto){
        try{
            var annotationResponse = annotationService.saveAnnotation(annotationRequestDto);
            return ResponseEntity.status(annotationResponse.getStatusCode()).body(annotationResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing annotation", description = "Update a annotation based on the provided data", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Annotation updated successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "422", description = "Annotation not updated due validation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Annotation to update not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity updateAnnotation(@Parameter(description = "ID of the annotation to be updated", required = true) @PathVariable(value = "id") Long id,
                                           @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request body containing annotation data", required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) @RequestBody AnnotationRequestDto annotationRequestDto){
        try{
            var annotationResponse = annotationService.updateAnnotation(id, annotationRequestDto);
            return ResponseEntity.status(annotationResponse.getStatusCode()).body(annotationResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
