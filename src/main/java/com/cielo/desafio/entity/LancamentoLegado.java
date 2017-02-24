package com.cielo.desafio.entity;

import lombok.Data;

import java.util.List;

@Data
public class LancamentoLegado {

    private TotalControleLancamento totalControleLancamento;
    private List<ControleLancamento> listaControleLancamento;
    private int indice;
    private int tamanhoPagina;
    private int totalElements;
}
