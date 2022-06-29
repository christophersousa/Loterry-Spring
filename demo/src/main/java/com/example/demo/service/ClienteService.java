package com.example.demo.service;

import java.math.BigDecimal;

import com.example.demo.model.Cliente;

public interface ClienteService {
    Cliente getUsername(String username);

    Cliente debitarCliente(String username, BigDecimal value) throws Exception;

    Cliente saveUser(Cliente cliente);
}
