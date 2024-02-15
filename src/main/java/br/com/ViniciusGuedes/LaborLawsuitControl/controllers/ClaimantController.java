package br.com.ViniciusGuedes.LaborLawsuitControl.controllers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.SaveOrUpdateResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.OnlyClaimantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.ClaimantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/claimants", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Claimant Controller", description = "API endpoints for managing claimants")
public class ClaimantController {

    private ClaimantService claimantService;

    public ClaimantController(ClaimantService claimantService) {
        this.claimantService = claimantService;
    }

    @GetMapping
    @Operation(summary = "Search for claimants", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved claimants", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "No claimant found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity findAllClaimants(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        try{
            Page<OnlyClaimantResponseDto> claimantResponse = claimantService.getAllClaimants(page, size);
            return ResponseEntity.ok(claimantResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{claimantId}")
    @Operation(summary = "Search for claimants by Id provided", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved claimant by id", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "No claimant found by id", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity findClaimantById(@Parameter(description = "ID of the claimant", required = true) @PathVariable(value = "claimantId") Long claimantId){
        try{
            ClaimantResponseDto claimantResponse = claimantService.getClaimantById(claimantId);
            return ResponseEntity.ok(claimantResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/searchByCpf")
    @Operation(summary = "Search for claimants by CPF provided", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved claimant by CPF", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "CPF provided does not meet the requirements", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "No claimant found by CPF", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity findClaimantByCpf(@Parameter(description = "CPF of the claimant", required = true) @RequestParam String cpf){
        try{
            ClaimantResponseDto claimantResponse = claimantService.getByCpf(cpf);
            return ResponseEntity.ok(claimantResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("/searchByName")
    @Operation(summary = "Search for claimants by name provided", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved claimant by name", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "Enter a name with at least 3 characters", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "No claimants found by name", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity findClaimantByName(@Parameter(description = "Name of the claimant", required = true) @RequestParam String claimantName,
    @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        try{
            Page<ClaimantResponseDto> claimantResponse = claimantService.getByName(claimantName, page, size);
            return ResponseEntity.ok(claimantResponse);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Create a new claimant", description = "Create a new claimant based on the provided data", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Claimant created successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "422", description = "Claimant not created due validation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity saveClaimant(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request body containing claimant data", required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) @RequestBody @Valid ClaimantRequestDto claimantRequestDto){
        try{
            SaveOrUpdateResponseDefault claimantResponse = claimantService.saveClaimant(claimantRequestDto);
            return ResponseEntity.status(claimantResponse.statusCode()).body(claimantResponse.message());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update an existing claimant", description = "Update a claimant based on the provided data", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Claimant updated successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "422", description = "Claimant not updated due validation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Claimant to update not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity updateClaimant(@Parameter(description = "ID of the claimant to be updated", required = true) @PathVariable(value = "id") Long id,
                                         @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request body containing claimant data", required = true,
                                                 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) @RequestBody @Valid ClaimantRequestDto claimantRequestDto){
        try{
            SaveOrUpdateResponseDefault claimantResponse = claimantService.updateClaimant(id, claimantRequestDto);
            return ResponseEntity.status(claimantResponse.statusCode()).body(claimantResponse.message());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
