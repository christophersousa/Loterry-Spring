package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.User;

public interface UserService {
    Optional<User> findByUsername(String username);

    User saveUser(User user);
}
