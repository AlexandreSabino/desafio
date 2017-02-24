package com.cielo.desafio.http.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by andre.oliveira on 2/23/17.
 */
@Data
@Builder
public class ExtratoDTO implements Serializable {
    private LocalDate dataLancamento;
    private String nomeTipoOperacao;
    private Long numeroRemessa;
    private String nomeSituacaoRemessa;
    private LocalDate dataConfirmacao;
    private DadosBancariosDTO dadosBancarios;
    private BigDecimal valorLancamentoRemessa;
    private String cnpj;
}
