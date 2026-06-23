package com.projects.kafka;



import com.projects.domain.model.ItemPedido;
import com.projects.domain.model.Pedido;
import com.projects.events.ItemEvent;

import java.util.List;

public interface KafkaService {
    void sendPedidoEmPreparacao(Pedido pedido);
    void sendPedidoDespachado(Pedido pedido);
}
