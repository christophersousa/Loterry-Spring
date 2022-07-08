package com.example.demo.service.visitor;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Aposta;
import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;
import com.example.demo.service.aposta.proxy.ProxyServiceAposta;

@Service
public class CreditarVisitor implements Visitor {
    @Autowired
    ClienteService clienteService;

    @Autowired
    ProxyServiceAposta apostaService;

    private BigDecimal PREMIO = new BigDecimal(0);

    public BigDecimal setPremio(BigDecimal premio) {
        return this.PREMIO = premio;
    }

    @Override
    public Cliente visit(Cliente cliente) {
        BigDecimal saldoAtual = cliente.getSaldo().add(PREMIO);
        cliente.setSaldo(saldoAtual);
        return clienteService.updateUser(cliente);
    }

    @Override
    public Aposta visit(Aposta aposta) {
        aposta.setValorPremio(PREMIO);
        aposta.setStatus(true);
        return apostaService.save(aposta);
    }

}
