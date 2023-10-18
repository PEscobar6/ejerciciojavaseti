package com.peescobar.ejerciciopractico.service;

import com.peescobar.ejerciciopractico.model.EnvioPedidoRequest;
import com.peescobar.ejerciciopractico.model.EnvioPedidoResponse;
import com.peescobar.ejerciciopractico.util.XmlJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final XmlJsonConverter xmlJsonConverter;
    private final RestTemplate restTemplate;
    private final String externalEndpoint;

    @Autowired
    public PedidoServiceImpl(XmlJsonConverter xmlJsonConverter, RestTemplate restTemplate,
                             @Value("${external.endpoint}") String externalEndpoint) {
        this.xmlJsonConverter = xmlJsonConverter;
        this.restTemplate = restTemplate;
        this.externalEndpoint = externalEndpoint;
    }
    @Override
    public EnvioPedidoResponse enviarPedido(EnvioPedidoRequest pedidoRequest) {
        int intentos = 3;
        String xmlRespuesta = null;
        String xmlSolicitud = xmlJsonConverter.convertirPedidoRequestAXml(pedidoRequest);

        // Realizar la solicitud al endpoint externo
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> requestEntity = new HttpEntity<>(xmlSolicitud, headers);
        while (intentos > 0) {
            try {
                xmlRespuesta = restTemplate.postForObject(externalEndpoint, pedidoRequest, String.class);
            } catch (HttpServerErrorException.GatewayTimeout e) {
                intentos--;
                if (intentos == 0) {
                    throw e;
                }
            }
        }

        EnvioPedidoResponse pedidoResponse = xmlJsonConverter.convertirXmlAPedidoResponse(xmlRespuesta);

        return pedidoResponse;
    }

}