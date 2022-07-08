package com.example.demo.service.protorype;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class ValorAposta {
    private Map<Integer, BigDecimal> cache = new HashMap<>();

    @PostConstruct
    public void initValor() {

        BigDecimal padrao = new BigDecimal(3.00);

        BigDecimal seteNumeros = new BigDecimal(15.00);

        BigDecimal oitoNumeros = new BigDecimal(90.00);

        BigDecimal noveNumeros = new BigDecimal(300.00);

        BigDecimal dezNumeros = new BigDecimal(1200.00);

        cache.put(6, padrao);
        cache.put(7, seteNumeros);
        cache.put(8, oitoNumeros);
        cache.put(9, noveNumeros);
        cache.put(10, dezNumeros);

    }

    public BigDecimal put(Integer key, BigDecimal valor) {
        cache.put(key, valor);
        return valor;
    }

    public BigDecimal get(Integer key) {
        if (!cache.containsKey(key)) {
            key = 3;
        }

        return cache.get(key);
    }
}
