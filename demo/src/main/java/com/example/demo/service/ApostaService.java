package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Aposta;
import com.example.demo.model.Cliente;
import com.example.demo.model.Sorteio;

public interface ApostaService {
    Aposta saveAposta(Aposta aposta, Cliente cliente) throws Exception;

    Aposta updateAposta(Aposta aposta);

    List<Aposta> getMinhasApostas(Cliente cliente);

    List<Aposta> findAllBySorteio(Sorteio sorteio);

    Optional<Aposta> favoritar(Integer id) throws Exception;

    Optional<Aposta> JogarNovamente(Integer id) throws Exception;

}
