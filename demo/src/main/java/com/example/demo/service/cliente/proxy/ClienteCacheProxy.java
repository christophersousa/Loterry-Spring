package com.example.demo.service.cliente.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.model.Aposta;
import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;

@Service
public class ClienteCacheProxy implements ProxyService {
    private ClienteRepository clienteRepository;
    private List<Cliente> cacheCliente = new ArrayList<Cliente>();

    public ClienteCacheProxy(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> findAll() {
        if (cacheCliente.isEmpty()) {
            cacheCliente = clienteRepository.findAll();
        } else {
            System.out.println("Retrieved list from cache.");
        }
        return cacheCliente;
    }

    @Override
    public Cliente save(Cliente cliente) {
        cacheCliente.add(cliente);
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Aposta> findByApostas(Cliente cliente) {
        return clienteRepository.findByApostas(cliente);
    }

    @Override
    public Cliente findByUsername(String username) {

        return clienteRepository.findByUsername(username);
    }

}
