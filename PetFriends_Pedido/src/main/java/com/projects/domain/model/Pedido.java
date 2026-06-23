package com.projects.domain.model;

import com.projects.domain.enums.StatusPedido;

import com.projects.domain.model.imports.Endereco;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String clienteId;

    private String clienteNome;

    @Embedded
    private Endereco endereco;

    @ElementCollection
    @CollectionTable(name = "pedido_itens", joinColumns = @JoinColumn(name = "pedido_id"))
    private List<ItemPedido> itens = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    private LocalDateTime dataCriacao;

    protected Pedido() {}

    public Pedido(String clienteId, String clienteNome,
                  Endereco endereco, List<ItemPedido> itens) {
        this.clienteId = clienteId;
        this.clienteNome = clienteNome;
        this.endereco = endereco;
        this.itens = itens;
        this.status = StatusPedido.NOVO;
        this.dataCriacao = LocalDateTime.now();
    }

    // ─── Comportamentos de negócio ────────────────────────

    public void fechar() {
        if (this.status != StatusPedido.NOVO) {
            throw new IllegalStateException("Pedido precisa estar NOVO para fechar");
        }
        this.status = StatusPedido.FECHADO;
    }

    public void iniciarPreparacao() {
        if (this.status != StatusPedido.FECHADO) {
            throw new IllegalStateException("Pedido precisa estar FECHADO para preparar");
        }
        this.status = StatusPedido.EM_PREPARACAO;
    }

    public void despachar() {
        if (this.status != StatusPedido.EM_PREPARACAO) {
            throw new IllegalStateException("Pedido precisa estar EM_PREPARACAO para despachar");
        }
        this.status = StatusPedido.DESPACHADO;
    }

    public void iniciarTransito() {
        if (this.status != StatusPedido.DESPACHADO) {
            throw new IllegalStateException("Pedido precisa estar DESPACHADO para transitar");
        }
        this.status = StatusPedido.EM_TRANSITO;
    }

    public void entregar() {
        if (this.status != StatusPedido.EM_TRANSITO) {
            throw new IllegalStateException("Pedido precisa estar EM_TRANSITO para entregar");
        }
        this.status = StatusPedido.ENTREGUE;
    }

    public void cancelar() {
        if (this.status == StatusPedido.ENTREGUE ||
                this.status == StatusPedido.DEVOLVIDO) {
            throw new IllegalStateException("Pedido não pode ser cancelado");
        }
        this.status = StatusPedido.CANCELADO;
    }

    public void devolver() {
        if (this.status != StatusPedido.EM_TRANSITO) {
            throw new IllegalStateException("Pedido precisa estar EM_TRANSITO para devolver");
        }
        this.status = StatusPedido.DEVOLVIDO;
    }

}