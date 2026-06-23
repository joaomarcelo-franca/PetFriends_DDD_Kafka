package com.projects.controller;

import com.projects.repository.PedidoRepository;
import com.projects.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class PetFriendsPedidoController {
    private final PedidoService pedidoService;

    @PostMapping("/almoxarifado/{pedidoId}")
    public ResponseEntity<String> almoxarifado(@PathVariable("pedidoId") String pedidoId) {
        pedidoService.confirmarPreparacao(pedidoId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transporte/{pedidoId}")
    public ResponseEntity<String> transporte(@PathVariable("pedidoId") String pedidoId) {
        pedidoService.despacharPedido(pedidoId);
        return ResponseEntity.ok().build();
    }

}
