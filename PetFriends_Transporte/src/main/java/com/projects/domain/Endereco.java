package com.projects.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
public class Endereco {

    @Column(nullable = false)
    private String rua;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false, length = 2)
    private String estado;

    @Column(nullable = false, length = 9)
    private String cep;

    protected Endereco() {}

    public Endereco(String rua, String bairro, String cidade, String estado, String cep) {
        if (rua == null || rua.isBlank()) throw new IllegalArgumentException("Rua é obrigatória");
        if (bairro == null || bairro.isBlank()) throw new IllegalArgumentException("Bairro é obrigatório");
        if (cidade == null || cidade.isBlank()) throw new IllegalArgumentException("Cidade é obrigatória");
        if (estado == null || !estado.matches("[A-Z]{2}")) throw new IllegalArgumentException("Estado inválido");
        if (cep == null || !cep.matches("\\d{5}-\\d{3}")) throw new IllegalArgumentException("CEP inválido");

        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }


}
