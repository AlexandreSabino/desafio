package com.cielo.desafio.config.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;

/**
 * Created by andre.oliveira on 2/23/17.
 */
public class LocalDateSerializerTest {

    DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private LocalDateSerializer serializer = new LocalDateSerializer(DEFAULT_FORMATTER);
    @Mock
    private JsonGenerator jsonGenerator;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void serialize() throws Exception {
        List<String> retorno = new ArrayList<>();
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            retorno.add((String)args[0]);
            return null;
        }).when(jsonGenerator).writeString(Mockito.anyString());

        LocalDate agora = LocalDate.now();
        String dateText = agora.format(DEFAULT_FORMATTER);

        serializer.serialize(agora, jsonGenerator,null);
        assertThat(dateText).isEqualTo(retorno.get(0));

    }

    @Test
    public void serializeNullLocalDate() throws Exception {
        List<String> retorno = new ArrayList<>();
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            retorno.add((String)args[0]);
            return null;
        }).when(jsonGenerator).writeString(Mockito.anyString());

        LocalDate agora = LocalDate.now();

        serializer.serialize(null, jsonGenerator,null);
        assertThat("").isEqualTo(retorno.get(0));

    }

}