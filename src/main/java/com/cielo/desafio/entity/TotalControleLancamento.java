package com.cielo.desafio.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TotalControleLancamento {

    private int quantidadeLancamentos;
    private int quantidadeRemessas;
    private BigDecimal valorLancamentos;
}
