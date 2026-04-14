package com.example.personality.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.personality.model.User;
import com.example.personality.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User signup(String username, String password, String fullName, String email) {
        repo.findByUsername(username).ifPresent(u -> {
            throw new IllegalArgumentException("Username already exists");
        });

        repo.findByEmail(email).ifPresent(u -> {
            throw new IllegalArgumentException("Email already exists");
        });

        String hash = encoder.encode(password);
        return repo.save(new User(username, hash, fullName, email));
    }

    // NEW: login using email (gmail)
    public User loginByEmail(String email, String password) {
        User user = repo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!encoder.matches(password, user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return user;
    }

    // Optional: keep old username login if you still need it somewhere
    public User login(String username, String password) {
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        if (!encoder.matches(password, user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        return user;
    }
}