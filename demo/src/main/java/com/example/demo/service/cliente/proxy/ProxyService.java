package com.example.demo.service.cliente.proxy;

import java.util.List;

import com.example.demo.model.Aposta;
import com.example.demo.model.Cliente;

public interface ProxyService {
    List<Cliente> findAll();

    Cliente save(Cliente cliente);

    Cliente findByUsername(String username);

    List<Aposta> findByApostas(Cliente cliente);
}
