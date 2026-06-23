package com.projects.events;

import com.projects.domain.model.Pedido;

import java.util.List;

public record PedidoEmPreparacaoEvent(
        String pedidoId,
        List<ItemEvent> itens
) {
    // método de fábrica — cria o evento a partir do Pedido
    public static PedidoEmPreparacaoEvent of(Pedido pedido) {
        List<ItemEvent> itens = pedido.getItens().stream()
                .map(item -> new ItemEvent(
                        item.getProdutoId(),
                        item.getQuantidade(),
                        item.getUnidade()
                ))
                .toList();

        return new PedidoEmPreparacaoEvent(pedido.getId(), itens);
    }
}
