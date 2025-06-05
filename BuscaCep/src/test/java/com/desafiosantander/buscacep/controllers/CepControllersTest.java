package com.desafiosantander.buscacep.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // Usa o profile de teste
public class CepControllersTest {

    private static final String MOCKED_RESULT =
        "{" +
            "\"cep\": \"18953007\"," +
            "\"rua\": \"Rua 2 rural, 500\"," +
            "\"bairro\": \"Jd dos Brilhantes\"," +
            "\"cidade\": \"Ipaussu\"," +
            "\"uf\": \"SP\"" +
            "}";

    private static WireMockServer wireMockeServer = new WireMockServer(options().port(8081));
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll() throws JsonProcessingException { // Inicializa antes de tudo
        wireMockeServer.start();
    }
    
    @BeforeEach
    void setUp() {
        wireMockeServer.resetAll(); // Para cada teste eu vou ter um cenário limpo 
    }
    
    @AfterAll
    static void afterAll() { // Depois que todos os testes foi executado ele para a aplicação do wiremock
        wireMockeServer.stop();
    }

    @Test
    public void testFindCep() throws Exception {
        wireMockeServer.stubFor(
                WireMock.get(urlPathEqualTo("/cep/18953007")) // Incluir o /cep/ no path
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(MOCKED_RESULT)));

        ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/ceps/18953007"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.cep").value("18953007"))
                .andExpect(jsonPath("$.rua").value("Rua 2 rural, 500"))
                .andExpect(jsonPath("$.bairro").value("Jd dos Brilhantes"))
                .andExpect(jsonPath("$.cidade").value("Ipaussu"))
                .andExpect(jsonPath("$.uf").value("SP"));
        
        Assertions.assertTrue(resultActions.andReturn().getResponse().getContentAsString().contains("Rua 2 rural, 500"));
        //Assertions.assertThrows(()-> {})
        //System.out.println(resultActions.andReturn().getResponse().getContentAsString());
    }
    
}