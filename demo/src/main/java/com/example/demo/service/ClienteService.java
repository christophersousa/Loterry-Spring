package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.model.Aposta;
import com.example.demo.model.Cliente;

public interface ClienteService {
    Cliente getUsername(String username);

    Cliente debitarCliente(String username, BigDecimal value) throws Exception;

    Cliente creditarCliente(String username, BigDecimal value) throws Exception;

    Cliente saveUser(Cliente cliente);

    List<Aposta> getMinhasApostas(Cliente cliente);

}
