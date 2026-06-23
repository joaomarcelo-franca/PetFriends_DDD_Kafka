package com.projects.events;

import com.projects.domain.model.Pedido;

public record TransporteEmRotaEvent(
        String pedidoId,
        String clienteNome,
        String rua,
        String bairro,
        String cidade,
        String estado,
        String cep
) {
    // método de fábrica — cria o evento a partir do Pedido
    public static TransporteEmRotaEvent of(Pedido pedido) {
        return new TransporteEmRotaEvent(
                pedido.getId(),
                pedido.getClienteNome(),
                pedido.getEndereco().getRua(),
                pedido.getEndereco().getBairro(),
                pedido.getEndereco().getCidade(),
                pedido.getEndereco().getEstado(),
                pedido.getEndereco().getCep()
        );
    }
}
