package com.cielo.desafio.config.converter;

import com.fasterxml.jackson.core.JsonParser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by andre.oliveira on 2/23/17.
 */
public class LocalDateDeserializerTest {

    DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    String DEFAULT_TEXT_DATE = "06/03/2016";

    private LocalDateDeserializer deserializer = new LocalDateDeserializer(DEFAULT_FORMATTER);
    @Mock
    private JsonParser jsonParser;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldDeserializerTextDate() throws Exception {
        Mockito.when(jsonParser.getText()).thenReturn(DEFAULT_TEXT_DATE);
        LocalDate localDate = deserializer.deserialize(jsonParser, null);
        assertThat(localDate).isEqualTo(LocalDate.parse(DEFAULT_TEXT_DATE, DEFAULT_FORMATTER));
    }

    @Test
    public void shouldDeserializerTextDateNull() throws Exception {
        Mockito.when(jsonParser.getText()).thenReturn(null);
        LocalDate localDate = deserializer.deserialize(jsonParser, null);
        assertThat(localDate).isEqualTo(null);
    }

    @Test
    public void shouldDeserializerTextDateBlank() throws Exception {
        Mockito.when(jsonParser.getText()).thenReturn("");
        LocalDate localDate = deserializer.deserialize(jsonParser, null);
        assertThat(localDate).isEqualTo(null);
    }

    @Test(expected = DateTimeParseException.class)
    public void shouldDeserializerTextDateErrorParse() throws Exception {
        Mockito.when(jsonParser.getText()).thenReturn("2017/02/22");
        LocalDate localDate = deserializer.deserialize(jsonParser, null);
    }

}