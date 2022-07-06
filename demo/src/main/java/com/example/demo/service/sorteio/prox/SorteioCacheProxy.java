package com.example.demo.service.sorteio.prox;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Sorteio;
import com.example.demo.repository.SorteioRepository;

@Service
public class SorteioCacheProxy implements ProxyServiceSorteio {

    private SorteioRepository sorteioRepository;

    private List<Sorteio> cacheSorteio = new ArrayList<Sorteio>();

    public SorteioCacheProxy(SorteioRepository sorteioRepository) {
        this.sorteioRepository = sorteioRepository;
    }

    @Override
    public List<Sorteio> findAll() {
        if (cacheSorteio.isEmpty()) {
            cacheSorteio = sorteioRepository.findAll();
        } else {
            System.out.println("Retrieved list from cache.");
        }
        return cacheSorteio;
    }

    @Override
    public List<Sorteio> findAllByOrderByIdDesc() {
        return sorteioRepository.findAllByOrderByIdDesc();
    }

    @Override
    public List<Sorteio> findByEnableTrue() {
        return sorteioRepository.findByEnableTrue();
    }

    @Override
    public Optional<Sorteio> findById(Integer id) {
        return sorteioRepository.findById(id);
    }

    @Override
    public Sorteio save(Sorteio sorteio) {
        cacheSorteio.add(sorteio);
        return sorteioRepository.save(sorteio);
    }

}
