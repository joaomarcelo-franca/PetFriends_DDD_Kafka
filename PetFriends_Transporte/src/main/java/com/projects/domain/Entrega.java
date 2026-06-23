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
@Table
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String pedidoid;

    @Embedded
    private Endereco endereco;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
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

    public Entrega(String pedidoid, Endereco endereco) {
        this.pedidoid = pedidoid;
        this.endereco = endereco;
    }
}
