package com.example.demo.service.cliente;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Aposta;
import com.example.demo.model.Authority;
import com.example.demo.model.Authority.AuthorityId;
import com.example.demo.model.Cliente;
import com.example.demo.model.User;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ClienteService;
import com.example.demo.service.cliente.proxy.ClienteCacheProxy;
import com.example.demo.service.cliente.proxy.ProxyService;

@Service
public class ClienteServiceImp implements ClienteService {
    private final UserRepository userRepository;
    private final AuthorityRepository AuthorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProxyService clienteRepository;

    private static final String USER_ROLE = "ROLE_USER";

    @Autowired
    public ClienteServiceImp(UserRepository userRepository, AuthorityRepository AuthorityRepository,
            PasswordEncoder passwordEncoder, ProxyService clienteRepository) {
        this.userRepository = userRepository;
        this.AuthorityRepository = AuthorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.clienteRepository = clienteRepository;

    }

    private User createUsers(Cliente cliente) {
        User user = new User();
        user.setUsername(cliente.getLogin());
        user.setPassword(passwordEncoder.encode(cliente.getPassword()));
        user.setEnabled(true);
        return userRepository.save(user);
    }

    private Authority createAuthorities(User user) {
        Authority authority = new Authority();
        authority.setAuthority(USER_ROLE);
        authority.setUsername(user);
        AuthorityId authorityId = new AuthorityId();
        authorityId.setUsername(user.getUsername());
        authorityId.setAuthority(USER_ROLE);
        authority.setId(
                authorityId);

        AuthorityRepository.save(authority);
        return authority;
    }

    @Override
    public Cliente saveUser(Cliente cliente) {
        // Encode plaintext password

        User user = createUsers(cliente);
        createAuthorities(user);
        cliente.setUser(user);
        cliente.setSaldo(new BigDecimal(0));
        return clienteRepository.save(cliente);
    }

    public Cliente updateUser(Cliente cliente) {
        // Encode plaintext password
        System.out.println("repo: " + cliente);
        cliente.setLogin(cliente.getUser().getUsername());
        ;
        cliente.setPassword(cliente.getUser().getPassword());
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente getUsername(String username) {
        return clienteRepository.findByUsername(username);
    }

    @Override
    public Cliente debitarCliente(String username, BigDecimal value) throws Exception {
        Cliente cliente = this.getUsername(username);
        BigDecimal saldo = cliente.getSaldo();
        BigDecimal zero = new BigDecimal(0.00);


        if (saldo.compareTo(zero) == 0) {
            System.out.println("********  Erro****");
            throw new Exception("Saldo não é suficiente!!");
        }

        cliente.setSaldo(saldo.subtract(value));
        updateUser(cliente);
        return cliente;
    }

    @Override
    public Cliente creditarCliente(String username, BigDecimal value) throws Exception {
        Cliente cliente = this.getUsername(username);
        BigDecimal saldo = cliente.getSaldo().add(value);

        if (saldo.equals(BigDecimal.ZERO)) {
            throw new Exception("Ocorreu um erro");
        }

        cliente.setSaldo(saldo);
        updateUser(cliente);
        return cliente;
    }

    public Cliente buscarCliente() {
        Cliente cliente = new Cliente();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String username = auth.getName();
            cliente = this.getUsername(username);

        }
        return cliente;
    }

    public List<Aposta> getMinhasApostas(Cliente cliente) {
        return clienteRepository.findByApostas(cliente);
    }

}
