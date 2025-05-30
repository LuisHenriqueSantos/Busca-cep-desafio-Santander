package com.desafiosantander.buscacep.controllers.docs;

import com.desafiosantander.buscacep.record.CepRecord;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface ICepController {


    @Operation(
            summary = "Encontre o CEP",
            description = "Encontra um código postal no Brasil",
            tags = {"código postal"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CepRecord.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @Parameter(name = "cep", description = "CEP a ser pesquisado", required = true)
    ResponseEntity<CepRecord> findCep(@PathVariable String cep);



}



