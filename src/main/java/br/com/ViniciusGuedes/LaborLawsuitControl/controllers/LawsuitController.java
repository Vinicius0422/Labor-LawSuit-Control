package br.com.ViniciusGuedes.LaborLawsuitControl.controllers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.LawsuitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/lawsuit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Lawsuit Controller", description = "API endpoints for managing lawsuits")
public class LawsuitController {

    private LawsuitService lawsuitService;

    public LawsuitController(LawsuitService lawsuitService) {
        this.lawsuitService = lawsuitService;
    }

    @GetMapping
    @Operation(summary = "Search for lawsuits", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved lawsuits", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "No lawsuit found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity findAllLawsuits(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        try {
            var lawsuitResponse = lawsuitService.getAllLawsuits(page, size);
            return ResponseEntity.status(lawsuitResponse.statusCode()).body(lawsuitResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{lawsuitId}")
    @Operation(summary = "Search for lawsuits by lawsuit Id provided", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved lawsuit by id", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "No lawsuit found by id", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity findLawsuitById(@Parameter(description = "ID of the lawsuit", required = true) @PathVariable Long lawsuitId){
        try{
            var lawsuitResponse = lawsuitService.getLawsuitById(lawsuitId);
            return ResponseEntity.status(lawsuitResponse.statusCode()).body(lawsuitResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/searchByLawsuitNumber")
    @Operation(summary = "Search for lawsuits by lawsuit number provided", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved lawsuit by number", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "Enter a lawsuit name with at least 16 characters", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "No lawsuit found by number", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity findLawsuitByNumber(@Parameter(description = "Number of the lawsuit", required = true) @RequestParam String lawsuitNumber){
        try{
            var lawsuitResponse = lawsuitService.getLawsuitByNumber(lawsuitNumber);
            return ResponseEntity.status(lawsuitResponse.statusCode()).body(lawsuitResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/searchLawsuitByClaimantName")
    @Operation(summary = "Search for lawsuits by claimant name provided", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved lawsuit by claimant name", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "Enter a claimant name with at least 3 characters", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "No lawsuit found by claimant name", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity findLawsuitByClaimantName(@Parameter(description = "claimant name of the lawsuits", required = true) @RequestParam String claimantName){
        try{
            var lawsuitResponse = lawsuitService.getLawsuitByClaimantName(claimantName);
            return ResponseEntity.status(lawsuitResponse.statusCode()).body(lawsuitResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Create a new lawsuit", description = "Create a new lawsuit based on the provided data", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lawsuit created successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "422", description = "Lawsuit not created due validation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity saveLawsuit(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request body containing lawsuit data", required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) @RequestBody @Valid LawsuitRequestDto lawsuitRequestDto){
        try{
            var lawsuitResponse = lawsuitService.saveLawsuit(lawsuitRequestDto);
            return ResponseEntity.status(lawsuitResponse.statusCode()).body(lawsuitResponse.message());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update an existing lawsuit", description = "Update a lawsuit based on the provided data", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lawsuit updated successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "422", description = "Lawsuit not updated due validation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Lawsuit to update not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity updateLawsuit(@Parameter(description = "ID of the lawsuit to be updated", required = true) @PathVariable(value = "id") Long id,
                                        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request body containing lawsuit data", required = true,
                                                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) @RequestBody @Valid LawsuitRequestDto lawsuitRequestDto){
        try{
            var lawsuitResponse = lawsuitService.updateLawsuit(id, lawsuitRequestDto);
            return ResponseEntity.status(lawsuitResponse.statusCode()).body(lawsuitResponse.message());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
