package com.cielo.desafio.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
@Configuration
@Component
@Data
public class ApplicationProperties {

    @Value("${spring.application.maxThread:4}")
    private Integer maxThread = 4;

}
