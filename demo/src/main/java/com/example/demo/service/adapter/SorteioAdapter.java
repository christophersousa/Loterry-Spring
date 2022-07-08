package com.example.demo.service.adapter;

import java.math.BigDecimal;

import com.example.demo.model.Aposta;
import com.example.demo.model.Sorteio;

public class SorteioAdapter extends Aposta {
    private Sorteio sort;

    public SorteioAdapter(Sorteio sort) {
        this.sort = sort;
    }

    @Override
    public BigDecimal getValorPremio() {
        return sort.getPremio();
    }

}
