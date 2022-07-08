package com.example.demo.service.visitor;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.model.Aposta;
import com.example.demo.model.Cliente;

public interface Visitor {

    BigDecimal setPremio(BigDecimal premio);

    Cliente visit(Cliente cliente);

    Aposta visit(Aposta aposta);

    default void getResult(List<Aposta> premiados) {
        premiados.forEach(aposta -> {
            aposta.accept(this);
            aposta.getCliente().accept(this);
        });
    }

}
