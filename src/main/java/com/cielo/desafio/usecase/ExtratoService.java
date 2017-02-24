package com.cielo.desafio.usecase;

import com.cielo.desafio.http.dto.ExtratoDTO;

import java.time.LocalDate;
import java.util.List;

public interface ExtratoService {

    List<ExtratoDTO> findAll();

    List<ExtratoDTO> findByCnpjAndDataLancamentoBetween(String cnpjCliente, LocalDate dataInicial, LocalDate dataFinal);
}
