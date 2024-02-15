package br.com.ViniciusGuedes.LaborLawsuitControl.controllers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress.ProgressRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.ProgressService;
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
@RequestMapping(value = "/progress", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Progress Controller", description = "API endpoints to manage the progress of labor lawsuits")
public class ProgressController {

    private ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping
    @Operation(summary = "Create a new progress", description = "Create a new progress based on the provided data", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Progress created successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "422", description = "Progress not created due validation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity saveProgress(@RequestBody ProgressRequestDto progressRequestDto){
        try{
            var progressResponse = progressService.saveProgress(progressRequestDto);
            return ResponseEntity.status(progressResponse.statusCode()).body(progressResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Create a new progress", description = "Update a progress based on the provided data", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Progress updated successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "422", description = "Progress not created due validation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Progress to update not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity updateProgress(@Parameter(description = "ID of the progress to be updated", required = true) @PathVariable(value = "id") Long id,
                                         @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request body containing annotation data", required = true,
                                                 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) @RequestBody ProgressRequestDto progressRequestDto){
        try{
            var progressResponse = progressService.updateProgress(id, progressRequestDto);
            return ResponseEntity.status(progressResponse.statusCode()).body(progressResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
