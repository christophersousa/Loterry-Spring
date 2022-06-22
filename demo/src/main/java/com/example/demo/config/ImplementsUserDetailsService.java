// package com.example.demo.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import
// org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Repository;

// import com.example.demo.model.User;
// import com.example.demo.repository.UserRepository;

// @Repository
// public class ImplementsUserDetailsService implements UserDetailsService {

// @Autowired
// private UserRepository clienteRepository;

// @Override
// public UserDetails loadUserByUsername(String username) throws
// UsernameNotFoundException {
// User cliente = clienteRepository.findByUsername(username);

// if (cliente == null) {
// throw new UsernameNotFoundException("Username not found");
// }

// return cliente;
// }

// }
