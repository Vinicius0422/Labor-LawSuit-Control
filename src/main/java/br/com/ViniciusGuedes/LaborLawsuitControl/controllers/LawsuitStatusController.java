package br.com.ViniciusGuedes.LaborLawsuitControl.controllers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.LawsuitStatusService;
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
@RequestMapping(value = "/lawsuitstatus", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Lawsuit Status Controller", description = "API endpoints for managing lawsuit status")
public class LawsuitStatusController {

    private LawsuitStatusService lawsuitStatusService;

    public LawsuitStatusController(LawsuitStatusService lawsuitStatusService) {
        this.lawsuitStatusService = lawsuitStatusService;
    }

    @GetMapping
    @Operation(summary = "Search for lawsuit status", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved lawsuit status", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "No lawsuit status found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity findAllLawsuitStatus(){
        try{
            var lawsuitStatusResponse = lawsuitStatusService.getAllLawsuitStatus();
            return ResponseEntity.status(lawsuitStatusResponse.getStatusCode()).body(lawsuitStatusResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
