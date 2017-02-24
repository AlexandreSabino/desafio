package com.cielo.desafio.config.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by andre.oliveira on 2/23/17.
 */
@AllArgsConstructor
public class LocalDateSerializer extends JsonSerializer<LocalDate> {

    private DateTimeFormatter formatter;

    @Override
    public void serialize(LocalDate localDate, JsonGenerator arg1, SerializerProvider arg2) throws IOException, JsonProcessingException {
        arg1.writeString(localDate != null ? localDate.format(formatter) : "");
    }
}