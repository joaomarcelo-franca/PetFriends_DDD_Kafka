package com.projects.domain;

import com.projects.domain.enums.StatusEntrega;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long pedidoid;

    @Embedded
    private Endereco endereco;

    @Column(nullable = false)
    private StatusEntrega status;

    public void registrarSaida(){
        this.status=StatusEntrega.EM_ROTA;
    }

    public void confirmarEntrega(){
        this.status=StatusEntrega.ENTREGUE;
    }

    public void registrarDevolucao(){
        this.status=StatusEntrega.DEVOLVIDO;
    }

    public Entrega(Long pedidoid, Endereco endereco) {
        this.pedidoid = pedidoid;
        this.endereco = endereco;
    }
}
