package com.cielo.desafio.usecase.impl;

import com.cielo.desafio.entity.ControleLancamento;
import com.cielo.desafio.http.dto.DadosBancariosDTO;
import com.cielo.desafio.http.dto.ExtratoDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class ExtratoConverter implements Converter<ControleLancamento, ExtratoDTO> {
    @Override
    public ExtratoDTO convert(ControleLancamento controleLancamento) {
        return  ExtratoDTO.builder()
                .dataLancamento(controleLancamento.getDataLancamentoContaCorrenteCliente())
                .nomeTipoOperacao(controleLancamento.getLancamentoContaCorrenteCliente().getTipoOperacao().toString())
                .numeroRemessa(controleLancamento.getLancamentoContaCorrenteCliente().getNumeroRemessaBanco())
                .nomeSituacaoRemessa(controleLancamento.getLancamentoContaCorrenteCliente().getSituacaoRemessa().toString())
                .dadosBancarios(DadosBancariosDTO.builder()
                                        .codigoBanco(controleLancamento.getLancamentoContaCorrenteCliente().getDadosDomicilioBancario().getCodigoBanco())
                                        .numeroAgencia(controleLancamento.getLancamentoContaCorrenteCliente().getDadosDomicilioBancario().getNumeroAgencia())
                                        .numeroContaCorrente(controleLancamento.getLancamentoContaCorrenteCliente().getDadosDomicilioBancario().getNumeroContaCorrente())
                                        .nomeBanco(controleLancamento.getNomeBanco()).build())
                .dataConfirmacao(controleLancamento.getDataEfetivaLancamento())
                .valorLancamentoRemessa(controleLancamento.getValorLancamentoRemessa())
                .cnpj(controleLancamento.getCnpj())
                .build();
    }
}
