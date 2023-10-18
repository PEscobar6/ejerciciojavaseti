package com.peescobar.ejerciciopractico.service;

import com.peescobar.ejerciciopractico.model.EnvioPedidoRequest;
import com.peescobar.ejerciciopractico.model.EnvioPedidoResponse;

public interface PedidoService {
    EnvioPedidoResponse enviarPedido(EnvioPedidoRequest pedidoRequest);
}