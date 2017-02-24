package com.cielo.desafio.http.util;

import com.cielo.desafio.config.response.WebPartialConfig;
import com.cielo.desafio.entity.LancamentoLegado;
import com.cielo.desafio.entity.TotalControleLancamento;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonHelperTest {
    private static final String pathJson = "lancamento-conta-legado.json";

    private static final String pathJsonList = "list-lancamento-conta-legado.json";

    private JsonHelper jsonHelper = new JsonHelper(new WebPartialConfig().getObjectMapper());

    @Test
    public void toObject() throws Exception {
        LancamentoLegado lancamentoLegado = jsonHelper.toObject(pathJson, LancamentoLegado.class);
        assertThat(lancamentoLegado.getIndice()).isEqualTo(1);
    }

    @Test
    public void objectToJson() throws Exception {
        TotalControleLancamento totalControleLancamento = new TotalControleLancamento();
        totalControleLancamento.setQuantidadeLancamentos(1);
        totalControleLancamento.setQuantidadeRemessas(2);
        totalControleLancamento.setValorLancamentos(BigDecimal.TEN);

        String json = jsonHelper.objectToJson(totalControleLancamento);
        assertThat(json).isNotNull();
    }

    @Test
    public void toListObect() throws Exception {
        List<LancamentoLegado> lancamentoLegados = jsonHelper.toListObect(pathJsonList, LancamentoLegado.class);
        assertThat(lancamentoLegados.size()).isEqualTo(2);
    }

    @Test(expected = RuntimeException.class)
    public void toListObjectInvalidPathTest() throws IOException {
        jsonHelper.toListObect("invalid_path", LancamentoLegado.class);
    }

    @Test(expected = RuntimeException.class)
    public void toObjectInvalidPathTest() throws IOException {

        jsonHelper.toObject("invalid_path", LancamentoLegado.class);

    }

    @Test(expected = RuntimeException.class)
    public void objectToJsonRuntimeException() throws JsonProcessingException {
        TotalControleLancamento totalControleLancamento = new TotalControleLancamento();

        ObjectMapper mock = Mockito.mock(ObjectMapper.class);
        Mockito.when(mock.writeValueAsString(Mockito.eq(totalControleLancamento)))
                .thenThrow(IOException.class);

        new JsonHelper(mock).objectToJson(totalControleLancamento);
    }

}