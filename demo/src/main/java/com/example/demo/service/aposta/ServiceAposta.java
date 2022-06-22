package com.example.demo.service.aposta;

import java.util.HashSet;
import java.util.List;

import com.example.demo.model.Aposta;

public class ServiceAposta {

    private void validationNumber(Integer value) throws Exception {
        if (value < 6) {
            throw new Exception("A quantidade mínima para fazer uma aposta é 6");
        } else if (value > 10) {
            throw new Exception("A quantidade máxima para fazer uma aposta é 10");
        }
    }

    private List<String> numbersAposta(List<String> numbers) {
        while (numbers.remove(null))
            ;
        System.out.println(numbers.size());
        numbers.remove(0);

        return numbers;
    }

    public Aposta setAposta(Aposta aposta) throws Exception {
        try {
            aposta.setNumeros(numbersAposta(aposta.getNumeros()));
            validationNumber(aposta.getNumeros().size());
        } catch (Exception e) {
            throw e;
        }
        return aposta;
    }
}
