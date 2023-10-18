package com.peescobar.ejerciciopractico.config;

import com.peescobar.ejerciciopractico.service.PedidoService;
import com.peescobar.ejerciciopractico.service.PedidoServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Value("${external.endpoint}")
    private String externalEndpoint;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}