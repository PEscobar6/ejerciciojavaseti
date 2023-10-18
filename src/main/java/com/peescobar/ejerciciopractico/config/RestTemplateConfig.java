package com.peescobar.ejerciciopractico.config;

import com.peescobar.ejerciciopractico.service.PedidoService;
import com.peescobar.ejerciciopractico.service.PedidoServiceImpl;
import com.peescobar.ejerciciopractico.util.XmlJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Value("${external.endpoint}")
    private String externalEndpoint;

    @Autowired
    private XmlJsonConverter xmlJsonConverter;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public PedidoService pedidoService(RestTemplate restTemplate) {
        return new PedidoServiceImpl(xmlJsonConverter, restTemplate, externalEndpoint);
    }
}