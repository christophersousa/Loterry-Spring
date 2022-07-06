package com.example.demo.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor()
@Entity
public class Aposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne()
    @JoinColumn(name = "id_sorteio")
    private Sorteio sorteio;

    @NumberFormat(pattern = "###,##0.00")
    @DecimalMin(value = "3", message = "Valor mínimo é 6 reais")
    private BigDecimal aposta;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> numeros;

    private BigDecimal premio;

    private Boolean status = false;

}
