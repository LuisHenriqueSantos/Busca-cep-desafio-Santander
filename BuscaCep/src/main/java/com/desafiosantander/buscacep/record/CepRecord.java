package com.desafiosantander.buscacep.record;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "código postal", description = "Registro de código CEP")
public record CepRecord(
        @NotBlank(message = "O código CEP é obrigatório") String cep,
        String rua,
        String bairro,
        String cidade,
        String uf
) {
}
