package com.brunov.api.shopping.atividadeniveisisolamento.controllers;

import com.brunov.api.shopping.atividadeniveisisolamento.entities.PedidoRequest;
import com.brunov.api.shopping.atividadeniveisisolamento.services.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/novo")
    public ResponseEntity<String> criarPedidoSemLocks(@RequestBody PedidoRequest request) {
        try {
            String response = pedidoService.criarPedidoSemLocks(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
