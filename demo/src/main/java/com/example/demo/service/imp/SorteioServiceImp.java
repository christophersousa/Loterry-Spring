package com.example.demo.service.imp;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cliente;
import com.example.demo.model.Sorteio;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.SorteioRepository;
import com.example.demo.service.SorteioService;

@Service
public class SorteioServiceImp implements SorteioService {

    private final ClienteRepository clienteRepository;

    private final SorteioRepository sorteioRepository;
    private final Date date = new Date();

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

        return numbers;
    }

    public List<Sorteio> SorteioEnabled() throws Exception {
        List<Sorteio> sorteioEnable = sorteioRepository.findByEnableTrue();
        if (sorteioEnable.isEmpty()) {
            throw new Exception("Não há jogo no momento, tente novamente mais tarde");
        }
        return sorteioEnable;
    }

    private void validationSorteio() throws Exception {
        List<Sorteio> sorteioEnable = sorteioRepository.findByEnableTrue();
        System.out.println(sorteioEnable);
        if (!sorteioEnable.isEmpty()) {
            throw new Exception("Já há um jogo cadastrado");
        }
    }

    @Override
    public Sorteio saveSorteio(Sorteio sorteio, Cliente cliente) throws Exception {
        try {
            sorteio.setNumerosSorteio(numbersSorteio(sorteio.getNumerosSorteio()));

            validationNumber(sorteio.getNumerosSorteio().size());
            validationSorteio();

            sorteio.setCliente(cliente);
            sorteio.setData(date);
            sorteio.setEnabled(true);
            sorteioRepository.save(sorteio);

        } catch (Exception e) {
            throw e;
        }
        return sorteio;
    }

    public List<Sorteio> getSorteios() {
        return sorteioRepository.findAllByOrderByIdDesc();
    }

}
