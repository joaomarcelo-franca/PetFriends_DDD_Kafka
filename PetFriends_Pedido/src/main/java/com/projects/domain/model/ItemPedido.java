package com.projects.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Embeddable
@Getter
@AllArgsConstructor
public class ItemPedido {

    @Column(nullable = false)
    private String produtoId;

    @Column(nullable = false)
    private String nomeProduto;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private String unidade;

    protected ItemPedido() {}
}