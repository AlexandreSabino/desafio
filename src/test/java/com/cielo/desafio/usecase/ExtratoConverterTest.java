package com.cielo.desafio.usecase;

import com.cielo.desafio.config.response.WebPartialConfig;
import com.cielo.desafio.entity.ControleLancamento;
import com.cielo.desafio.entity.LancamentoLegado;
import com.cielo.desafio.http.dto.ExtratoDTO;
import com.cielo.desafio.http.util.JsonHelper;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by andre.oliveira on 2/24/17.
 */
public class ExtratoConverterTest {

    private static final String pathJson = "lancamento-conta-legado.json";

    private JsonHelper jsonHelper = new JsonHelper(new WebPartialConfig().getObjectMapper());

    private ExtratoConverter extratoConverter = new ExtratoConverter();

    LancamentoLegado lancamentoLegado = jsonHelper.toObject(pathJson, LancamentoLegado.class);

    @Test
    public void convert() throws Exception {
        lancamentoLegado.getListaControleLancamento().forEach(controleLancamento -> {
            ExtratoDTO extratoDTO = extratoConverter.convert(controleLancamento);
            verify(controleLancamento, extratoDTO);
        });
    }

    private void verify(ControleLancamento controleLancamento, ExtratoDTO extratoDTO) {
        assertThat(extratoDTO.getDataLancamento()).isEqualTo(controleLancamento.getDataLancamentoContaCorrenteCliente());
        assertThat(extratoDTO.getNomeTipoOperacao()).isEqualTo(controleLancamento.getLancamentoContaCorrenteCliente().getTipoOperacao().toString());
        assertThat(extratoDTO.getNumeroRemessa()).isEqualTo(controleLancamento.getLancamentoContaCorrenteCliente().getNumeroRemessaBanco());
        assertThat(extratoDTO.getNomeSituacaoRemessa()).isEqualTo(controleLancamento.getLancamentoContaCorrenteCliente().getSituacaoRemessa().toString());

        assertThat(extratoDTO.getDadosBancarios().getCodigoBanco()).isEqualTo(controleLancamento.getLancamentoContaCorrenteCliente().getDadosDomicilioBancario().getCodigoBanco());
        assertThat(extratoDTO.getDadosBancarios().getNumeroAgencia()).isEqualTo(controleLancamento.getLancamentoContaCorrenteCliente().getDadosDomicilioBancario().getNumeroAgencia());
        assertThat(extratoDTO.getDadosBancarios().getNumeroContaCorrente()).isEqualTo(controleLancamento.getLancamentoContaCorrenteCliente().getDadosDomicilioBancario().getNumeroContaCorrente());
        assertThat(extratoDTO.getDadosBancarios().getNomeBanco()).isEqualTo(controleLancamento.getNomeBanco());

        assertThat(extratoDTO.getDataConfirmacao()).isEqualTo(controleLancamento.getDataEfetivaLancamento());
        assertThat(extratoDTO.getValorLancamentoRemessa()).isEqualTo(controleLancamento.getValorLancamentoRemessa());


    }


}