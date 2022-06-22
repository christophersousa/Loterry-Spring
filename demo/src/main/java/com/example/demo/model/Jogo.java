package com.example.demo.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor()
@Entity
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NumberFormat(pattern = "###,##0.00")
    @NotNull(message = "O valor do premio do jogo é obrigatório")
    @DecimalMin(value = "5000", message = "Valor mínimo é 5000 reais")
    private BigDecimal premio;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> numeros;
}
