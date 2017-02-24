package com.cielo.desafio.gateway;

import com.cielo.desafio.entity.LancamentoLegado;

import java.util.Collection;

/**
 * Created by andre.oliveira on 2/23/17.
 */
public interface LancamentoLegadoRepository {
    Collection<LancamentoLegado> findAll();
}
