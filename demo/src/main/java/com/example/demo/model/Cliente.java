package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor()
@Entity
public class Cliente {

    // Dados Pessoais
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "O campo nome é obrigatório!")
    private String nome;

    @NotNull(message = "Login é valor obrigatório!")
    private String username;

    @Past(message = "O campo data de nascimento deve ser passado!")
    @NotNull(message = "O campo data de nascimento é obrigató")
    private Date dataNascimento;

    @NotNull(message = "O campo CPF é obrigató")
    private String cpf;

    @OneToMany(mappedBy = "cliente")
    private List<Aposta> apostas;

    //Login


    @NotNull(message = "Senha é valor obrigatório")
    private String senha;
}
