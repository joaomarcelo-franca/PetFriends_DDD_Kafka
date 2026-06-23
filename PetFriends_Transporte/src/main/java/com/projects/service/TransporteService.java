package com.projects.service;

import com.projects.domain.Endereco;
import com.projects.domain.Entrega;
import com.projects.events.TransporteEmRotaEvent;
import com.projects.repository.EntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransporteService {
    private final EntregaRepository entregaRepository;

    @Transactional
    public void processarEntrega(TransporteEmRotaEvent event) {
        if (entregaRepository.existsByPedidoid(Long.parseLong(event.pedidoId()))) {
            throw new RuntimeException("Já existe entrega para o pedido: " + event.pedidoId());
        }

        Endereco endereco = new Endereco(
                event.rua(),
                event.bairro(),
                event.cidade(),
                event.estado(),
                event.cep()
        );

        Entrega entrega = new Entrega(
                Long.parseLong(event.pedidoId()),
                endereco
        );

        entrega.registrarSaida();
        entregaRepository.save(entrega);
    }
}
