package com.example.demo.service.aposta.proxy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Aposta;
import com.example.demo.model.Sorteio;
import com.example.demo.repository.ApostaRepository;

@Service
public class ApostaCacheProxy implements ProxyServiceAposta {

    List<Aposta> cacheApostas = new ArrayList<Aposta>();

    private ApostaRepository apostaRepository;

    public ApostaCacheProxy(ApostaRepository apostaRepository) {
        this.apostaRepository = apostaRepository;
    }

    @Override
    public List<Aposta> findAll() {
        if (cacheApostas.isEmpty()) {
            cacheApostas = apostaRepository.findAll();
        } else {
            System.out.println("Retrieved list from cache.");
        }
        return cacheApostas;
    }

    @Override
    public List<Aposta> findAllByAposta(String username) {
        return apostaRepository.findAllByAposta(username);
    }

    @Override
    public List<Aposta> findAllBySorteio(Sorteio sorteio) {
        return apostaRepository.findAllBySorteio(sorteio);
    }

    @Override
    public Aposta save(Aposta aposta) {
        return apostaRepository.save(aposta);
    }

}
