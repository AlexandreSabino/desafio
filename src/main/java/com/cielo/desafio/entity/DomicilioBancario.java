package com.cielo.desafio.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class DomicilioBancario implements Serializable {

    private int codigoBanco;
    private int numeroAgencia;
    private String numeroContaCorrente;

}
