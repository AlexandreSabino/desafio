package com.cielo.desafio.usecase.impl;

import com.cielo.desafio.config.ApplicationProperties;
import com.cielo.desafio.entity.ControleLancamento;
import com.cielo.desafio.gateway.LancamentoLegadoRepository;
import com.cielo.desafio.http.dto.ExtratoDTO;
import com.cielo.desafio.http.dto.PeriodoDTO;
import com.cielo.desafio.usecase.ExtratoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by andre.oliveira on 2/23/17.
 */
@Component
@AllArgsConstructor()
@Slf4j
public class ExtratoServiceImpl implements ExtratoService {

    private final LancamentoLegadoRepository lancamentoLegadoRepository;

    private final ExtratoConverter extratoConverter;

    private final ApplicationProperties applicationProperties;

    @Override
    public List<ExtratoDTO> findByCnpjAndDataLancamentoBetween(String cnpjCliente, PeriodoDTO periodoDTO) {
        return lancamentoLegadoRepository.findAll().stream()
                .flatMap(lancamentoLegado -> lancamentoLegado.getListaControleLancamento().stream().
                        filter(getFilter(cnpjCliente, periodoDTO.getDataInicial(), periodoDTO.getDataFinal())).
                        map(controleLancamento -> runAsync(controleLancamento)))
                .sorted(Comparator.comparing(ExtratoDTO::getDataLancamento))
                .collect(Collectors.toList());
    }

    private ExtratoDTO runAsync(ControleLancamento controleLancamento) throws RuntimeException {

        try {
            return CompletableFuture.supplyAsync(() -> extratoConverter.convert(controleLancamento),
                                                       Executors.newFixedThreadPool(applicationProperties.getMaxThread()))
                    .get();
        } catch (Exception e) {
            log.error("Erro ao conveter dados do extrato", e);
            throw  new RuntimeException(e.getMessage());
        }
    }

    private Predicate<? super ControleLancamento> getFilter(String cnpjCliente, LocalDate dataInicial, LocalDate dataFinal) {
        return controleLancamento -> controleLancamento.getCnpj().equals(cnpjCliente) &&
                (controleLancamento.getDataEfetivaLancamento().isAfter(dataInicial) || controleLancamento.getDataEfetivaLancamento().equals(dataInicial) ) &&
                (controleLancamento.getDataEfetivaLancamento().isBefore(dataFinal) || controleLancamento.getDataEfetivaLancamento().equals(dataFinal));

    }
}
