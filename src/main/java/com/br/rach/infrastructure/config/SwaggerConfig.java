package com.br.rach.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {

        OpenAPI openAPI = new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("API RachApp")
                        .description("RachApp - Dividir nunca foi tão fácil")
                        .version("v1")
                        .contact(new Contact()
                                .name("Alex Soares")
                                .email("alexsaosilva@gmail.com")
                                .url("https://github.com/Led-ds")));

        return openAPI;
    }
}