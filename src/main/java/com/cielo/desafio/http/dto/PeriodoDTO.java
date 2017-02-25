package com.cielo.desafio.http.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
public class PeriodoDTO implements Serializable {

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @ApiModelProperty(dataType = "java.lang.String", example = "dd/MM/yyyy")
    private LocalDate dataInicial;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @ApiModelProperty(dataType = "java.lang.String", example = "dd/MM/yyyy")
    private LocalDate dataFinal;

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }
}
