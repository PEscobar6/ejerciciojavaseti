package com.peescobar.ejerciciopractico.util;

import com.peescobar.ejerciciopractico.model.EnvioPedidoRequest;

import com.peescobar.ejerciciopractico.model.EnvioPedidoResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Component;

import java.io.StringReader;
import java.io.StringWriter;

@Component
public class XmlJsonConverter {
    public String convertirPedidoRequestAXml(EnvioPedidoRequest pedidoRequest) {
        try {
            // Crear un contexto JAXB para la clase PedidoRequest
            JAXBContext context = JAXBContext.newInstance(EnvioPedidoRequest.class);

            // Crear el objeto Marshaller para convertir el objeto a XML
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Convertir PedidoRequest a XML
            StringWriter writer = new StringWriter();
            marshaller.marshal(pedidoRequest, writer);

            return writer.toString();
        } catch (JAXBException e) {
            // Manejar excepciones de JAXB
            e.printStackTrace();
            return null;
        }
    }

    public EnvioPedidoResponse convertirXmlAPedidoResponse(String xmlRespuesta) {
        try {
            JAXBContext context = JAXBContext.newInstance(EnvioPedidoResponse.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            StringReader reader = new StringReader(xmlRespuesta);
            EnvioPedidoResponse pedidoResponse = (EnvioPedidoResponse) unmarshaller.unmarshal(reader);

            return pedidoResponse;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
