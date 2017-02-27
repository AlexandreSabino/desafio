package com.cielo.desafio.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
public class ControleLancamento implements Serializable {

    private LancamentoContaCorrenteCliente lancamentoContaCorrenteCliente;
    private LocalDate dataEfetivaLancamento;
    private LocalDate dataLancamentoContaCorrenteCliente;
    private int numeroEvento;
    private String descricaoGrupoPagamento;
    private String codigoIdentificadorUnico;
    private String nomeBanco;
    private int quantidadeLancamentoRemessa;
    private String numeroRaizCNPJ;
    private String numeroSufixoCNPJ;
    private BigDecimal valorLancamentoRemessa;
    private Date dateLancamentoContaCorrenteCliente;
    private Date dateEfetivaLancamento;

    public String getCnpj() {
        return numeroRaizCNPJ.concat(String.format("%06d", Integer.parseInt(numeroSufixoCNPJ)));
    }
}
