package com.example.demo.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor()
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Transient
    @NotEmpty(message = "O campo username é obrigatório!")
    private String login;

    @Transient
    @NotNull(message = "O campo data de nascimento é obrigató")
    private String password;

    @NotEmpty(message = "O campo nome é obrigatório!")
    private String nome;

    @NotBlank(message = "Campo obrigatório!")
    @Email
    private String email;

    @Past(message = "O campo data de nascimento deve ser passado!")
    @NotNull(message = "O campo data de nascimento é obrigató")
    private Date dataNascimento;

    @NotNull(message = "O campo CPF é obrigató")
    private String cpf;

    @NumberFormat(pattern = "###,##0.00")
    private BigDecimal saldo;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Aposta> apostas;

    @OneToOne
    @JoinColumn(name = "username")
    private User user;

}
