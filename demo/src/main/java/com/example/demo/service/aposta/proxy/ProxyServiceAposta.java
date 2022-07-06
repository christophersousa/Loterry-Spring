package com.example.demo.service.aposta.proxy;

import java.util.List;

import com.example.demo.model.Aposta;
import com.example.demo.model.Sorteio;

public interface ProxyServiceAposta {
    List<Aposta> findAll();

    Aposta save(Aposta aposta);

    List<Aposta> findAllByAposta(String username);

    List<Aposta> findAllBySorteio(Sorteio sorteio);
}
