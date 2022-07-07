package com.example.demo.service.biuld;

import com.example.demo.model.Cliente;
import com.example.demo.model.User;

public interface Builder {
    Builder saveUser(String username, String password, Boolean enabled);

    Builder saveAuthority(User user, String role);

    Builder saveCliente(Cliente cliente);
}
