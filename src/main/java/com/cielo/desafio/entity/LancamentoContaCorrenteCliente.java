package com.cielo.desafio.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LancamentoContaCorrenteCliente implements Serializable {

    private Long numeroRemessaBanco;
    @JsonProperty("nomeSituacaoRemessa")
    private SituacaoRemessa situacaoRemessa;
    private DomicilioBancario dadosDomicilioBancario;
    @JsonProperty("nomeTipoOperacao")
    private TipoOperacao tipoOperacao;
    private List<DadosAnaliticosFinaceiro> dadosAnaliticoLancamentoFinanceiroCliente;

}
