package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Cliente;
import com.example.demo.model.Sorteio;

public interface SorteioService {
    Sorteio saveSorteio(Sorteio sorteio, Cliente cliente) throws Exception;

    List<Sorteio> SorteioEnabled() throws Exception;

    Optional<Sorteio> liberarSorteio(Integer id) throws Exception;

}
