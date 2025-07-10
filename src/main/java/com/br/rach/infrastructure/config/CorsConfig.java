package com.br.rach.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //Política de permissões de CORS
        registry.addMapping("/**") //Todos os endpoints da API
                .allowedOrigins("http://localhost:8081") //servidores
                .allowedMethods("POST", "PUT", "DELETE", "GET") //métodos
                .allowedHeaders("*"); //parametros de cabeçalho das requisições
    }
}