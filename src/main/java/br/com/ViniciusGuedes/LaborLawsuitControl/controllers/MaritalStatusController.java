package br.com.ViniciusGuedes.LaborLawsuitControl.controllers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.MaritalStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/maritalstatus", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Marital Status Controller", description = "API endpoints for managing marital status")
public class MaritalStatusController {

    private MaritalStatusService  maritalStatusService;

    public MaritalStatusController(MaritalStatusService maritalStatusService) {
        this.maritalStatusService = maritalStatusService;
    }

    @GetMapping
    @Operation(summary = "Search for marital status", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved marital status", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "No marital status found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity findAllMaritalStatus(){
        try{
            var maritalStatusResponse = maritalStatusService.getAllMaritalStatus();
            return ResponseEntity.status(maritalStatusResponse.statusCode()).body(maritalStatusResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
