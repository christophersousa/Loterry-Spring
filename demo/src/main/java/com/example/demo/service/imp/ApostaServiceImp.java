package com.example.demo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Aposta;
import com.example.demo.repository.ApostaRepository;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.SorteioRepository;
import com.example.demo.service.ApostaService;

@Service
public class ApostaServiceImp implements ApostaService {

    private final ClienteRepository clienteRepository;

    private final ApostaRepository apostaRepository;

    @Autowired
    public ApostaServiceImp(ApostaRepository apostaRepository, ClienteRepository clienteRepository) {
        this.apostaRepository = apostaRepository;
        this.clienteRepository = clienteRepository;
    }

    private void validationNumber(Integer value) throws Exception {
        if (value < 6) {
            throw new Exception("A quantidade mínima para fazer uma Sorteio é 6");
        } else if (value > 10) {
            throw new Exception("A quantidade máxima para fazer uma Sorteio é 10");
        }
    }

    private List<String> numbersAposta(List<String> numbers) {
        while (numbers.remove(null))
            ;
        System.out.println(numbers.size());
        numbers.remove(0);

        return numbers;
    }

    @Override
    public Aposta saveAposta(Aposta aposta) throws Exception {
        try {
            aposta.setNumeros(numbersAposta(aposta.getNumeros()));

            validationNumber(aposta.getNumeros().size());

            apostaRepository.save(aposta);

        } catch (Exception e) {
            throw e;
        }
        return aposta;
    }

}
