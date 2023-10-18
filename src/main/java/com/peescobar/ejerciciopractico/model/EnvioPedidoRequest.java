package com.peescobar.ejerciciopractico.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
@Data
@XmlRootElement
public class EnvioPedidoRequest {
    private String numPedido;
    private String cantidadPedido;
    private String codigoEAN;
    private String nombreProducto;
    private String numDocumento;
    private String direccion;
}
