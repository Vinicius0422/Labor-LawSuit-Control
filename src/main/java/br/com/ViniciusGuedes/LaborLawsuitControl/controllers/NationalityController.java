package br.com.ViniciusGuedes.LaborLawsuitControl.controllers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.NationalityService;
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
@RequestMapping(value = "/nationality", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Nationality Controller", description = "API endpoints for managing nationalities")
public class NationalityController {

    private NationalityService nationalityService;

    public NationalityController(NationalityService nationalityService) {
        this.nationalityService = nationalityService;
    }

    @GetMapping
    @Operation(summary = "Search for nationalities", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved nationalities", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "No nationality found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity findAllNationalities(){
        try{
            var nationalityResponse = nationalityService.getAllNationalities();
            return ResponseEntity.status(nationalityResponse.statusCode()).body(nationalityResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
