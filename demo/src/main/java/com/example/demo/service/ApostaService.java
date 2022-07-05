package com.example.demo.service;

import com.example.demo.model.Aposta;
import com.example.demo.model.Cliente;

public interface ApostaService {
    Aposta saveAposta(Aposta aposta, Cliente cliente) throws Exception;
}
