package com.projects.events;

public record TransporteEmRotaEvent(
    String pedidoId,
    String clienteNome,
    String rua,
    String bairro,
    String cidade,
    String estado,
    String cep
    ){
}
