package com.example.demo.service.biuld;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.Cliente;
import com.example.demo.model.User;

public class ClienteBuilder implements Builder {

    private final PasswordEncoder passwordEncoder;
    private final User user;

    public ClienteBuilder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.user = new User();
    }

    @Override
    public ClienteBuilder saveAuthority(User user, String role) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ClienteBuilder saveCliente(Cliente cliente) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ClienteBuilder saveUser(String username, String password, Boolean enabled) {
        User u = new User();
        u.setUsername(username);
        u.setPassword(passwordEncoder.encode(password));
        u.setEnabled(true);
        return this;
    }

}
