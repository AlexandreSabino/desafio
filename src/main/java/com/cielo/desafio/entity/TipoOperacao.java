package com.cielo.desafio.entity;

/**
 * Created by andre.oliveira on 2/24/17.
 */
public enum TipoOperacao {

    REGULAR("regular"), VARIAVEL("variavel");

    private String descricao;

    TipoOperacao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
