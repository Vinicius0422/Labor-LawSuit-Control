package br.com.ViniciusGuedes.LaborLawsuitControl.controllers;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.DefendantService;
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
@RequestMapping(value = "/defendant", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Defendant Controller", description = "API endpoints for managing defendants")
public class DefendantController {

    private DefendantService defendantService;

    public DefendantController(DefendantService defendantService) {
        this.defendantService = defendantService;
    }

    @GetMapping
    @Operation(summary = "Search for defendants", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved defendants", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "No defendant found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity findAllDefendants(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        try{
            var defendantResponse = defendantService.getAllDefendants(page, size);
            return ResponseEntity.status(defendantResponse.getStatusCode()).body(defendantResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{defendantId}")
    @Operation(summary = "Search for defendants by Id provided", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved defendant by id", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "No defendant found by id", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity findDefendantById(@Parameter(description = "ID of the defendant", required = true) @PathVariable(value = "defendantId") Long defendantId){
        try{
            var defendantResponse = defendantService.getDefendantById(defendantId);
            return ResponseEntity.status(defendantResponse.getStatusCode()).body(defendantResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/searchByName")
    @Operation(summary = "Search for defendants by name provided", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved defendant by name", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "Enter a name with at least 3 characters", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "No defendants found by name", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity findDefendantsByName(@Parameter(description = "Name of the defendant", required = true) @RequestParam String defendantName){
        try{
            var defendantResponse = defendantService.getDefendantByName(defendantName);
            return ResponseEntity.status(defendantResponse.getStatusCode()).body(defendantResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/searchByCpfOrCnpj")
    @Operation(summary = "Search for defendants by CPF or CNPJ provided", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved defendant by CPF/CNPJ", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "CPF/CNPJ provided does not meet the requirements", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "No defendant found by CPF/CNPJ", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity findDefendantByCpfOrCnpj(@Parameter(description = "CPF/CNPJ of the defendant", required = true) @RequestParam String cpfCnpj){
        try{
            var defendantResponse = defendantService.getDefendantByCpfOrCnpj(cpfCnpj);
            return ResponseEntity.status(defendantResponse.getStatusCode()).body(defendantResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Create a new defendant", description = "Create a new defendant based on the provided data", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Defendant created successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "422", description = "Defendant not created due validation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity saveDefendant(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request body containing defendant data", required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) @RequestBody DefendantRequestDto defendantRequestDto){
        try{
            var defendantResponse = defendantService.saveDefendant(defendantRequestDto);
            return ResponseEntity.status(defendantResponse.getStatusCode()).body(defendantResponse.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update an existing defendant", description = "Update a defendant based on the provided data", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Defendant updated successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "422", description = "Defendant not updated due validation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Defendant to update not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity updateDefendant(@Parameter(description = "ID of the defendant to be updated", required = true) @PathVariable(value = "id") Long id,
                                          @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request body containing defendant data", required = true,
                                                  content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) @RequestBody DefendantRequestDto defendantRequestDto){
        try{
            var defendantResponse = defendantService.updateDefendant(id, defendantRequestDto);
            return ResponseEntity.status(defendantResponse.getStatusCode()).body(defendantResponse.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
