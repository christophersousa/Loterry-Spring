package com.example.demo;

// import java.math.BigDecimal;
// import java.util.Date;
// import java.util.HashSet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import com.example.demo.model.Cliente;
// import com.example.demo.model.Controlador;
// import com.example.demo.model.Jogo;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		// Controlador cont = new Controlador();
		// cont.setNome("Administrador");
		// cont.setUsername("Admin");
		// cont.setCpf("888.888.888-88");
		// cont.setDataNascimento(new Date("00/00/0000"));
		// cont.setPassword("12345");

		// HashSet<Integer> numbers = new HashSet<Integer>();
		// numbers.add(5);
		// numbers.add(6);
		// numbers.add(3);
		// numbers.add(8);
		// numbers.add(1);
		// numbers.add(9);

		// Jogo jogo1 = new Jogo();
		// jogo1.setNumeros(numbers);
		// jogo1.setCriador(cont);
		// jogo1.setPremio(new BigDecimal(15000));

		// Cliente user = new Cliente();
		// user.setNome("Ana Maria");
		// user.setUsername("maria");
		// user.setCpf("888.888.888-88");
		// user.setDataNascimento(new Date("00/00/0000"));
		// user.setSaldo(new BigDecimal(1000));
		// user.setPassword("12345");

		// Cliente user2 = new Cliente();
		// user2.setNome("João Alves");
		// user2.setUsername("alves");
		// user2.setCpf("888.888.888-88");
		// user2.setDataNascimento(new Date("00/00/0000"));
		// user2.setSaldo(new BigDecimal(1000));
		// user2.setPassword("12345");
	}

}
