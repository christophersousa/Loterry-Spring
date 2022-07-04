package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.model.Sorteio;

public interface SorteioService {
    Sorteio saveSorteio(Sorteio sorteio, Cliente cliente) throws Exception;
}
