package com.cielo.desafio.http;

import com.cielo.desafio.ExtratoApplication;
import com.cielo.desafio.entity.LancamentoLegado;
import com.cielo.desafio.gateway.LancamentoLegadoRepository;
import com.cielo.desafio.http.dto.PeriodoDTO;
import com.cielo.desafio.http.error.ExceptionTranslator;
import com.cielo.desafio.http.util.JsonHelper;
import com.cielo.desafio.usecase.ExtratoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExtratoApplication.class)
public class ExtratoControlerIntTest {

    @Autowired
    private ExtratoService extratoService;

    @Mock
    private LancamentoLegadoRepository lancamentoLegadoRepository;

    private MockMvc restExtratoMockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private JsonHelper jsonHelper;

    private DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ExtratoControler extratoControler = new ExtratoControler(extratoService);

        ReflectionTestUtils.setField(extratoService, "lancamentoLegadoRepository", lancamentoLegadoRepository);

        final StaticApplicationContext applicationContext = new StaticApplicationContext();
        applicationContext.registerSingleton("exceptionHandler", ExceptionTranslator.class);
        final WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();
        webMvcConfigurationSupport.setApplicationContext(applicationContext);

        this.restExtratoMockMvc = MockMvcBuilders.standaloneSetup(extratoControler)
                .setHandlerExceptionResolvers(webMvcConfigurationSupport.handlerExceptionResolver())
                .setMessageConverters(new MappingJackson2HttpMessageConverter())

                .build();
    }

    @Test
    public void getAllExtratosByFilter() throws Exception {
        String pathJson = "lancamento-conta-legado.json";
        Mockito.when(lancamentoLegadoRepository.findAll()).thenReturn(Arrays.asList(jsonHelper.toObject(pathJson, LancamentoLegado.class)));

        PeriodoDTO periodoDTO = new PeriodoDTO();
        periodoDTO.setDataInicial(LocalDate.parse("02/06/2016", DEFAULT_FORMATTER));
        periodoDTO.setDataFinal(LocalDate.parse("04/06/2016", DEFAULT_FORMATTER));

        restExtratoMockMvc.perform(post("/api/extrato/{cnpj}", "12996721001597")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mapper.writeValueAsBytes(periodoDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].nomeTipoOperacao").value(hasItem("regular")))
                .andExpect(jsonPath("$.[*].numeroRemessa").value(hasItem(64320626054L)))
                .andExpect(jsonPath("$.[*].dadosBancarios.codigoBanco").value(hasItem(123)))
                .andExpect(jsonPath("$.[*].dadosBancarios.numeroAgencia").value(hasItem(1)))
                .andExpect(jsonPath("$.[*].dadosBancarios.numeroContaCorrente").value(hasItem("00000000065470")))
                .andExpect(jsonPath("$.[*].dadosBancarios.nomeBanco").value(hasItem("BANCO ABCD S.A.")))
                .andExpect(jsonPath("$.[*].valorLancamentoRemessa").value(hasItem(11499.1)));
    }

    @Test
    public void shoudReturnBadRequestErrorConversion() throws Exception {
        String pathJson = "lancamento-conta-legado-erro.json";
        Mockito.when(lancamentoLegadoRepository.findAll()).thenReturn(Arrays.asList(jsonHelper.toObject(pathJson, LancamentoLegado.class)));

        PeriodoDTO periodoDTO = new PeriodoDTO();
        periodoDTO.setDataInicial(LocalDate.parse("02/06/2016", DEFAULT_FORMATTER));
        periodoDTO.setDataFinal(LocalDate.parse("04/06/2016", DEFAULT_FORMATTER));

        restExtratoMockMvc.perform(post("/api/extrato/{cnpj}", "12996721001597")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mapper.writeValueAsBytes(periodoDTO)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldReturnBadRequest() throws Exception {
        PeriodoDTO periodoDTO = new PeriodoDTO();
        periodoDTO.setDataInicial(LocalDate.parse("02/06/2016", DEFAULT_FORMATTER));

        restExtratoMockMvc.perform(post("/api/extrato/{cnpj}", "12996721001597")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mapper.writeValueAsBytes(periodoDTO)))
                .andExpect(status().isBadRequest());
    }



}