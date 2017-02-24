package com.cielo.desafio.gateway.impl;

import com.cielo.desafio.entity.LancamentoLegado;
import com.cielo.desafio.gateway.LancamentoLegadoRepository;
import com.cielo.desafio.http.util.JsonHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by andre.oliveira on 2/23/17.
 */
@Component
@AllArgsConstructor
public class LancamentoLegadoRepositoryImpl implements LancamentoLegadoRepository {

    private final JsonHelper jsonHelper;

    private static final String pathJson = "lancamento-conta-legado.json";

    @Override
    public Collection<LancamentoLegado> findAll() {
        return Arrays.asList(jsonHelper.toObject(pathJson, LancamentoLegado.class));
    }
}
