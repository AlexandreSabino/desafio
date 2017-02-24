package com.cielo.desafio.usecase.impl;

import com.cielo.desafio.entity.ControleLancamento;
import com.cielo.desafio.gateway.LancamentoLegadoRepository;
import com.cielo.desafio.http.dto.ExtratoDTO;
import com.cielo.desafio.usecase.ExtratoConverter;
import com.cielo.desafio.usecase.ExtratoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by andre.oliveira on 2/23/17.
 */
@Component
@AllArgsConstructor
public class ExtratoServiceImpl implements ExtratoService {

    private final LancamentoLegadoRepository lancamentoLegadoRepository;

    private final ExtratoConverter extratoConverter;

    @Override
    public List<ExtratoDTO> findAll() {
        return lancamentoLegadoRepository.findAll().stream()
                .flatMap(lancamentoLegado -> lancamentoLegado.getListaControleLancamento().stream().map(
                        controleLancamento -> extratoConverter.convert(controleLancamento)))
                .sorted(Comparator.comparing(ExtratoDTO::getDataLancamento))
                .collect(Collectors.toList());
    }

    @Override
    public List<ExtratoDTO> findByCnpjAndDataLancamentoBetween(String cnpjCliente, LocalDate dataInicial, LocalDate dataFinal) {
        return lancamentoLegadoRepository.findAll().stream()
                .flatMap(lancamentoLegado -> lancamentoLegado.getListaControleLancamento().stream().
                        filter(getFilter(cnpjCliente, dataInicial, dataFinal)).
                        map(controleLancamento -> extratoConverter.convert(controleLancamento)))
                .sorted(Comparator.comparing(ExtratoDTO::getDataLancamento))
                .collect(Collectors.toList());
    }

    private Predicate<? super ControleLancamento> getFilter(String cnpjCliente, LocalDate dataInicial, LocalDate dataFinal) {
        return controleLancamento -> controleLancamento.getCnpj().equals(cnpjCliente) &&
                (controleLancamento.getDataEfetivaLancamento().isAfter(dataInicial) || controleLancamento.getDataEfetivaLancamento().equals(dataInicial) ) &&
                (controleLancamento.getDataEfetivaLancamento().isBefore(dataFinal) || controleLancamento.getDataEfetivaLancamento().equals(dataFinal));

    }


}
