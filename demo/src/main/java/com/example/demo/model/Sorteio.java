package com.example.demo.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
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
public class Sorteio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @NumberFormat(pattern = "###,##0.00")
    @NotNull(message = "O valor do premio do jogo é obrigatório")
    @DecimalMin(value = "500", message = "Valor mínimo é 6reais")
    private BigDecimal premio;

    private Date data;

    private Boolean enabled;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> numerosSorteio;

    @OneToMany(mappedBy = "sorteio", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Aposta> apostas;

}
