package com.example.demo.service.cliente;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Aposta;
import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;
import com.example.demo.service.biuld.Builder;
import com.example.demo.service.cliente.proxy.ProxyService;

@Service
public class ClienteServiceImp implements ClienteService {
    private final ProxyService clienteRepository;
    private final Builder builder;

    @Autowired
    public ClienteServiceImp(
            ProxyService clienteRepository, Builder builder) {
        this.clienteRepository = clienteRepository;
        this.builder = builder;

    }

    @Override
    public Cliente saveUser(Cliente cliente) {

        builder.setUser(cliente.getLogin(), cliente.getPassword());
        builder.setAuthority(cliente.getLogin());
        Cliente novoCliente = builder.getResult(cliente);

        return clienteRepository.save(novoCliente);
    }

    @Override
    public Cliente updateUser(Cliente cliente) {
        // Encode plaintext password
        cliente.setLogin(cliente.getUser().getUsername());
        ;
        cliente.setPassword(cliente.getUser().getPassword());
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente getUsername(String username) {
        return clienteRepository.findByUsername(username);
    }

    @Override
    public Cliente debitarCliente(String username, BigDecimal value) throws Exception {
        Cliente cliente = this.getUsername(username);
        BigDecimal saldo = cliente.getSaldo();
        BigDecimal zero = new BigDecimal(0.00);

        if (saldo.compareTo(zero) == 0) {
            throw new Exception("Saldo não é suficiente!!");
        }

        cliente.setSaldo(saldo.subtract(value));
        updateUser(cliente);
        return cliente;
    }

    @Override
    public Cliente creditarCliente(String username, BigDecimal value) throws Exception {
        Cliente cliente = this.getUsername(username);
        BigDecimal saldo = cliente.getSaldo().add(value);

        if (saldo.equals(BigDecimal.ZERO)) {
            throw new Exception("Ocorreu um erro");
        }

        cliente.setSaldo(saldo);
        updateUser(cliente);
        return cliente;
    }

    @Override
    public Cliente buscarCliente() {
        Cliente cliente = new Cliente();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String username = auth.getName();
            cliente = this.getUsername(username);

        }
        return cliente;
    }

    @Override
    public List<Aposta> getMinhasApostas(Cliente cliente) {
        return clienteRepository.findByApostas(cliente);
    }

}
