package com.cielo.desafio.http.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

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
