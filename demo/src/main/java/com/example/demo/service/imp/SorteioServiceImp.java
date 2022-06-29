package com.example.demo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Sorteio;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.SorteioRepository;
import com.example.demo.service.SorteioService;

@Service
public class SorteioServiceImp implements SorteioService {

    private final ClienteRepository clienteRepository;

    private final SorteioRepository sorteioRepository;

    @Autowired
    public SorteioServiceImp(SorteioRepository sorteioRepository, ClienteRepository clienteRepository) {
        this.sorteioRepository = sorteioRepository;
        this.clienteRepository = clienteRepository;
    }

    private void validationNumber(Integer value) throws Exception {
        if (value < 6) {
            throw new Exception("A quantidade mínima para fazer uma Sorteio é 6");
        } else if (value > 10) {
            throw new Exception("A quantidade máxima para fazer uma Sorteio é 10");
        }
    }

    private List<String> numbersSorteio(List<String> numbers) {
        while (numbers.remove(null))
            ;
        System.out.println(numbers.size());
        numbers.remove(0);

        return numbers;
    }

    @Override
    public Sorteio saveSorteio(Sorteio sorteio, String name) throws Exception {
        try {
            sorteio.setNumerosSorteio(numbersSorteio(sorteio.getNumerosSorteio()));

            validationNumber(sorteio.getNumerosSorteio().size());

            sorteio.setCliente(clienteRepository.findByUsername(name));
            sorteioRepository.save(sorteio);

        } catch (Exception e) {
            throw e;
        }
        return sorteio;
    }

}
