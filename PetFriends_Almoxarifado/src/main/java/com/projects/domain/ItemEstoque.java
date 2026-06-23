package com.projects.domain;

import com.projects.domain.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Entity
public class ItemEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(name = "produto_id", nullable = false)
    private Long produtoid;

    @Embedded
    private Quantidade quantidade;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "valor",
                    column = @Column(name = "quantidade_reservada_valor")),
            @AttributeOverride(name = "unidade",
                    column = @Column(name = "quantidade_reservada_unidade"))
    })
    private Quantidade quantidadeReservada;

    public void reservar(Quantidade necessaria) {
        if (!this.quantidade.isSuficiente(necessaria)) {
            throw new RuntimeException("Estoque insuficiente para reserva");
        }
        this.quantidade = this.quantidade.subtrair(necessaria);
        this.quantidadeReservada = this.quantidadeReservada.somar(necessaria);
        atualizarStatus();
    }

    private void atualizarStatus() {
        if (this.quantidade.getValor() == 0) {
            this.status = Status.SEM_ESTOQUE;
        } else {
            this.status = Status.COM_ESTOQUE;
        }
    }

    protected ItemEstoque() {}
}
