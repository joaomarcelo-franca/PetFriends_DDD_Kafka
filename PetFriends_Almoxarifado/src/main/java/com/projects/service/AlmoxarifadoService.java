package com.projects.service;

import com.projects.domain.ItemEstoque;
import com.projects.domain.Quantidade;
import com.projects.events.PedidoEmPreparacaoEvent;
import com.projects.repository.ItemEstoqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlmoxarifadoService {

    private final ItemEstoqueRepository itemEstoqueRepository;

    @Transactional
    public void processarPedidoEmPreparacao(PedidoEmPreparacaoEvent event) {
        event.itens().forEach(item -> {

            ItemEstoque itemEstoque = itemEstoqueRepository
                    .findByProdutoid(Long.parseLong(item.produtoId()))
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + item.produtoId()));

            Quantidade quantidadeNecessaria = new Quantidade(
                    item.quantidade(),
                    item.unidade()
            );

            itemEstoque.reservar(quantidadeNecessaria);
        });

    }
}
