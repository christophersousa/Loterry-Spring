package com.example.demo.service.biuld;

import com.example.demo.model.Authority;
import com.example.demo.model.Cliente;
import com.example.demo.model.User;

public interface Builder {

    ClienteBuilder setUser(String login, String password);

    ClienteBuilder setAuthority(String username);

    Cliente getResult(Cliente cliente);
}
