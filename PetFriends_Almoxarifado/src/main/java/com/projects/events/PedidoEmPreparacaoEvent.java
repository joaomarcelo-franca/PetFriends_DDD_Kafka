package com.projects.events;

import java.util.List;

public record PedidoEmPreparacaoEvent(
        String pedidoId,
        List<ItemEvent> itens
        ) {
}

