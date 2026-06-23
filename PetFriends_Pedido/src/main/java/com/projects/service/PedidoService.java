package com.projects.service;

import com.projects.domain.model.Pedido;
import com.projects.kafka.KafkaService;
import com.projects.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final KafkaService kafkaService;

    @Transactional
    public void confirmarPreparacao(String pedidoId) {

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.iniciarPreparacao();
        pedidoRepository.save(pedido);
        kafkaService.sendPedidoEmPreparacao(pedido);
    }

    @Transactional
    public void despacharPedido(String pedidoId) {

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.despachar();
        pedidoRepository.save(pedido);
        kafkaService.sendPedidoDespachado(pedido);
    }
}
