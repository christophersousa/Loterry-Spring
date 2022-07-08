package com.example.demo.service.biuld;

import java.math.BigDecimal;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Authority;
import com.example.demo.model.Authority.AuthorityId;
import com.example.demo.model.Cliente;
import com.example.demo.model.User;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.cliente.proxy.ProxyService;

@Service
public class ClienteBuilder implements Builder {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private User user = new User();
    private Authority authority = new Authority();

    private static final String USER_ROLE = "ROLE_USER";

    public ClienteBuilder(PasswordEncoder passwordEncoder,
            AuthorityRepository authorityRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Cliente getResult(Cliente cliente) {
        cliente.setUser(this.user);
        cliente.setSaldo(new BigDecimal(0));

        return cliente;
    }

    @Override
    public ClienteBuilder setAuthority(String username) {

        Authority authority = new Authority();
        authority.setAuthority(USER_ROLE);
        authority.setUsername(user);

        AuthorityId authorityId = new AuthorityId();
        authorityId.setUsername(username);
        authorityId.setAuthority(USER_ROLE);
        authority.setId(authorityId);
        authorityRepository.save(authority);
        this.authority = authority;
        return this;
    }

    @Override
    public ClienteBuilder setUser(String login, String password) {
        User user = new User();
        user.setUsername(login);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);
        userRepository.save(user);
        this.user = user;
        return this;
    }

}
