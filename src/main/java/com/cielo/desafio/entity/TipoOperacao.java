package com.cielo.desafio.entity;

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
