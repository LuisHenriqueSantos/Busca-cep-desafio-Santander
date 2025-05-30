package com.desafiosantander.buscacep.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("API do CEP")
                        .version("v1")
                        .description("Esta é uma API o CEP de localização no Brasil")
                        .termsOfService("https://github.com/LuisHenriqueSantos")
                        .license(new License()
                                .name("none")
                                .url("https://github.com/LuisHenriqueSantos")
                        )
                );
    }
}