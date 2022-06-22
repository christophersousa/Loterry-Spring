package com.example.demo.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor()
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "O campo nome é obrigatório!")
    private String nome;

    @NotEmpty(message = "O campo nome é obrigatório!")
    private String username;

    @Past(message = "O campo data de nascimento deve ser passado!")
    @NotNull(message = "O campo data de nascimento é obrigató")
    private Date dataNascimento;

    @NotNull(message = "O campo CPF é obrigató")
    private String cpf;

    @NotNull(message = "O campo data de nascimento é obrigató")
    @Size(min = 5, max = 18, message = "A senha deve conter de 5 à 18 caracters")
    private String password;
}
