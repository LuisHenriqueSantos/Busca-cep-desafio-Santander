package com.desafiosantander.buscacep.service;

import com.desafiosantander.buscacep.entities.HistoricoLog;
import com.desafiosantander.buscacep.exceptions.ResourceNotFoundException;
import com.desafiosantander.buscacep.record.CepRecord;
import com.desafiosantander.buscacep.repository.HistoricoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class CepService implements ICepService {

    private final RestTemplate restTemplate;
    private final HistoricoRepository repository;
    private final ObjectMapper objectMapper;

    @Value("${zip.api.url}")
    private String urlBase;

    public CepService(RestTemplate restTemplate, HistoricoRepository repository, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public CepRecord buscaCep(String cep) {
        CepRecord response = findZipCodeWiremocks(cep);
        saveLogQuery(cep, response);
        return response;
    }

    private CepRecord findZipCodeWiremocks(String cep) {
        String url = urlBase + cep;
        try {
            CepRecord response = restTemplate.getForObject(url, CepRecord.class);
            if (response == null) {
                throw new ResourceNotFoundException("Cep: " + cep + " não encontrado!");
            }
            return response;
        } catch (HttpClientErrorException.NotFound ex) {
            throw new ResourceNotFoundException("Cep: " + cep + " não encontrado!");
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao consultar o serviço Cep", ex);
        }
    }

    private void saveLogQuery(String zipCode, CepRecord response) {
        var log = HistoricoLog.builder()
                .cep(zipCode)
                .rua(response.rua())
                .cidade(response.cidade())
                .uf(response.uf())
                .dataHora(LocalDateTime.now())
                .build();
        repository.save(log);
    }
}
