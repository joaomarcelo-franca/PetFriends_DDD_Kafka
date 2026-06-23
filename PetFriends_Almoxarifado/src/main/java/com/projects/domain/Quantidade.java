package com.projects.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Embeddable
public class Quantidade {

    @Column(nullable = false)
    private int valor;

    @Column(nullable = false)
    private String unidade;

    public boolean isSuficiente(Quantidade necessaria) {
        return this.valor >= necessaria.valor;
    }

    public Quantidade subtrair(Quantidade outra) {
        if (this.valor < outra.valor) {
            throw new IllegalArgumentException("Valor insuficiente para subtração");
        }
        return new Quantidade(this.valor - outra.valor, this.unidade);
    }

    public Quantidade somar(Quantidade outra) {
        return new Quantidade(this.valor + outra.valor, this.unidade);
    }

    public Quantidade(int valor, String unidade) {
        if (valor < 0) {
            throw new IllegalArgumentException("A quantidade não pode ser negativa.");
        }
        this.valor = valor;
        this.unidade = unidade;
    }

    public int getValor() {
        return valor;
    }

    public String getUnidade() {
        return unidade;
    }

    protected  Quantidade() {
    }
}
