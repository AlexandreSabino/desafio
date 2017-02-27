package com.cielo.desafio.http.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Data
public class PeriodoDTO implements Serializable {

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @ApiModelProperty(dataType = "java.lang.String", example = "dd/MM/yyyy")
    private LocalDate dataInicial;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @ApiModelProperty(dataType = "java.lang.String", example = "dd/MM/yyyy")
    private LocalDate dataFinal;

}
