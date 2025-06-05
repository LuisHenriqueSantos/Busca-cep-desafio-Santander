package com.desafiosantander.buscacep.controllers;

import com.desafiosantander.buscacep.record.CepRecord;
import com.desafiosantander.buscacep.service.ICepService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ceps")
@Tag(name = "cep", description = "Pesquisa do Cep")
public class CepController {
    
    private final ICepService zipCodeService;

    @GetMapping("/{cep}")
    public ResponseEntity<CepRecord> findCep(@PathVariable String cep) {
        CepRecord resposta = zipCodeService.buscaCep(cep);
        return ResponseEntity.ok(resposta);
    }
}