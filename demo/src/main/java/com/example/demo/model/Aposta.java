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
import javax.persistence.OneToOne;
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

    @OneToOne()
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @NumberFormat(pattern = "###,##0.00")
    @NotNull(message = "O valor do premio do jogo é obrigatório")
    @DecimalMin(value = "6", message = "Valor mínimo é 6reais")
    private BigDecimal aposta;

    @ElementCollection(fetch = FetchType.EAGER)
    @NotNull
    private List<String> numeros;

}
