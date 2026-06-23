package com.projects.kafka;

import com.projects.domain.model.ItemPedido;
import com.projects.domain.model.Pedido;
import com.projects.events.ItemEvent;
import com.projects.events.PedidoEmPreparacaoEvent;
import com.projects.events.TransporteEmRotaEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void sendPedidoEmPreparacao(Pedido pedido) {
        List<ItemEvent> itens = pedido.getItens().stream()
                .map(item -> new ItemEvent(
                        item.getProdutoId(),
                        item.getQuantidade(),
                        item.getUnidade()
                ))
                .toList();

        PedidoEmPreparacaoEvent event = new PedidoEmPreparacaoEvent(
                pedido.getId(),
                itens
        );

        System.out.println("[KAFKA - PRODUCER] Enviando evento 'pedido-em-preparacao' | pedidoId=" + pedido.getId() + " | itens=" + itens.size());
        kafkaTemplate.send("pedido-em-preparacao", pedido.getId(), event);
        System.out.println("[KAFKA - PRODUCER] Evento 'pedido-em-preparacao' enviado com sucesso | pedidoId=" + pedido.getId());
    }

    @Override
    public void sendPedidoDespachado(Pedido pedido) {
        TransporteEmRotaEvent event = new TransporteEmRotaEvent(
                pedido.getId(),
                pedido.getClienteNome(),
                pedido.getEndereco().getRua(),
                pedido.getEndereco().getBairro(),
                pedido.getEndereco().getCidade(),
                pedido.getEndereco().getEstado(),
                pedido.getEndereco().getCep()
        );

        System.out.println("[KAFKA - PRODUCER] Enviando evento 'transporte-em-rota' | pedidoId=" + pedido.getId() + " | cliente=" + pedido.getClienteNome());
        kafkaTemplate.send("transporte-em-rota", pedido.getId(), event);
        System.out.println("[KAFKA - PRODUCER] Evento 'transporte-em-rota' enviado com sucesso | pedidoId=" + pedido.getId());
    }

}
