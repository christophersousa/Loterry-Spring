package com.example.demo.service.sorteio.prox;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Sorteio;

public interface ProxyServiceSorteio {

    Sorteio save(Sorteio sorteio);

    List<Sorteio> findAll();

    List<Sorteio> findAllByOrderByIdDesc();

    List<Sorteio> findByEnableTrue();

    Optional<Sorteio> findById(Integer id);
}
