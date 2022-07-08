package com.example.demo.service.sorteio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Aposta;
import com.example.demo.model.Cliente;
import com.example.demo.model.Sorteio;
import com.example.demo.repository.ApostaRepository;
import com.example.demo.service.ClienteService;
import com.example.demo.service.SorteioService;
import com.example.demo.service.sorteio.prox.ProxyServiceSorteio;
import com.example.demo.service.visitor.CreditarVisitor;
import com.example.demo.service.visitor.Visitor;

@Service
public class SorteioServiceImp implements SorteioService {

    private final ProxyServiceSorteio sorteioRepository;

    private final ApostaRepository apostaRepository;

    private final CreditarVisitor visitor;

    private final Date date = new Date();

    @Autowired
    public SorteioServiceImp(ProxyServiceSorteio sorteioRepository,
            ApostaRepository apostaRepository, CreditarVisitor creditarVisitor) {
        this.sorteioRepository = sorteioRepository;
        this.apostaRepository = apostaRepository;
        this.visitor = creditarVisitor;
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

    @Override
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

    public Optional<Sorteio> getById(Integer id) {
        return sorteioRepository.findById(id);
    }

    private void creditarPremiados(BigDecimal valor, List<Aposta> premiados) throws Exception {
        BigDecimal premio = valor.divide(new BigDecimal(premiados.size()));
        visitor.setPremio(premio);
        visitor.getResult(premiados);

    }

    private void validarPremiados(Sorteio id) {
        List<Aposta> apostas = apostaRepository.findAllBySorteio(id);

        List<Aposta> premiados = new ArrayList<Aposta>();

        apostas.forEach(p -> {
            if (p.getNumeros().containsAll(id.getNumerosSorteio())) {
                premiados.add(p);
            }
        });

        try {
            creditarPremiados(id.getPremio(), premiados);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Optional<Sorteio> liberarSorteio(Integer id) throws Exception {
        Optional<Sorteio> sort = getById(id);
        try {

            if (!sort.isPresent()) {
                throw new Exception("Sorteio não encontrado");
            } else if (sort.get().getEnabled() != true) {
                throw new Exception("Sorteio Já sorteado");
            }

            validarPremiados(sort.get());

            sort.get().setEnabled(false);
            sorteioRepository.save(sort.get());

        } catch (Exception e) {
            throw e;
        }
        return sort;
    }

}
