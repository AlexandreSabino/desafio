package com.cielo.desafio.entity;

/**
 * Created by andre.oliveira on 2/24/17.
 */
public enum SituacaoRemessa {

    PAGO("Pago"), ABERTO("Aberto");

    private String descricao;

    SituacaoRemessa(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
