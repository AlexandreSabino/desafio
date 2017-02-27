package com.cielo.desafio.http.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class DadosBancariosDTO implements Serializable {

    private int codigoBanco;
    private int numeroAgencia;
    private String numeroContaCorrente;
    private String nomeBanco;
    
}
