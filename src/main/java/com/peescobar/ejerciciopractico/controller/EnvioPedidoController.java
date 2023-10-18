package com.peescobar.ejerciciopractico.controller;

import com.peescobar.ejerciciopractico.model.EnvioPedidoRequest;
import com.peescobar.ejerciciopractico.model.EnvioPedidoResponse;
import com.peescobar.ejerciciopractico.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class EnvioPedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public EnvioPedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/enviar")
    public EnvioPedidoResponse enviarPedido(@RequestBody EnvioPedidoRequest pedidoRequest) {
        return pedidoService.enviarPedido(pedidoRequest);
    }
}
