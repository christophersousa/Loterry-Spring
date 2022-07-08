package com.example.demo.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;

import org.springframework.format.annotation.NumberFormat;

import com.example.demo.service.visitor.Visitor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor()
@Entity
public class Aposta extends Model {
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

    @Column(name = "premio")
    private BigDecimal valorPremio;

    private Boolean status = false;

    private Boolean favorito = false;

    @Override
    public Aposta accept(Visitor visitor) {
        return visitor.visit(this);
    }

}
