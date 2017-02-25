package com.cielo.desafio.usecase;

import com.cielo.desafio.http.dto.ExtratoDTO;
import com.cielo.desafio.http.dto.PeriodoDTO;

import java.util.List;

public interface ExtratoService {

    List<ExtratoDTO> findByCnpjAndDataLancamentoBetween(String cnpjCliente, PeriodoDTO periodoDTO);
}
