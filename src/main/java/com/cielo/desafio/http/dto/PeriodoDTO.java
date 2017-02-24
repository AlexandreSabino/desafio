package com.cielo.desafio.http.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PeriodoDTO implements Serializable {

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicial;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFinal;
}
