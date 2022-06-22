package com.example.demo.service.imp;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityrepository;
    private final PasswordEncoder passwordEncoder;

    private static final String USER_ROLE = "ROLE_USER";

    @Autowired
    public UserServiceImp(UserRepository userRepository, AuthorityRepository authorityrepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityrepository = authorityrepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        // Encode plaintext password
        user.setPasswords(passwordEncoder.encode(user.getPasswords()));
        user.setEnabled(true);
        // Set Role to ROLE_USER
        return userRepository.saveAndFlush(user);
    }

}
