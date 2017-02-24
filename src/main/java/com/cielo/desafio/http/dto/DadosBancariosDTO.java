package com.cielo.desafio.http.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by andre.oliveira on 2/23/17.
 */
@Data
@Builder
public class DadosBancariosDTO implements Serializable {

    private int codigoBanco;
    private int numeroAgencia;
    private String numeroContaCorrente;
    private String nomeBanco;
    
}
