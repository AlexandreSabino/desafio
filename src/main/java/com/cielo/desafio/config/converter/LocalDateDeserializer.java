package com.cielo.desafio.config.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    private DateTimeFormatter formatter;

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext arg1) throws IOException {
        return !StringUtils.isEmpty(jsonParser.getText()) ? LocalDate.parse(jsonParser.getText(), formatter) : null;
    }
}