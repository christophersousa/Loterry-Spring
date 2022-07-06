package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Aposta;
import com.example.demo.model.Cliente;
import com.example.demo.model.Sorteio;

public interface ApostaService {
    Aposta saveAposta(Aposta aposta, Cliente cliente) throws Exception;

    List<Aposta> getMinhasApostas(Cliente cliente);

    List<Aposta> findAllBySorteio(Sorteio sorteio);

}
