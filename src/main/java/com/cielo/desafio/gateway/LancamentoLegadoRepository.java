package com.cielo.desafio.gateway;

import com.cielo.desafio.entity.LancamentoLegado;

import java.util.Collection;

public interface LancamentoLegadoRepository {
    Collection<LancamentoLegado> findAll();
}
