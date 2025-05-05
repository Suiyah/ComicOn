package org.example.comicon.service;

import org.example.comicon.entities.User;
import org.example.comicon.repository.UserRepository;
import org.example.comicon.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public boolean register(String username, String rawPassword) {
        if (userRepository.findByUsername(username).isPresent()) {
            return false;
        }
        String encodedPassword = passwordEncoder.encode(rawPassword);
        User user = User.builder()
                .username(username)
                .password(encodedPassword)
                .build();
        userRepository.save(user);
        return true;
    }

    public String login(String username, String rawPassword) {
        return userRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(rawPassword, user.getPassword()))
                .map(user -> jwtUtil.generateToken(user.getUsername()))
                .orElse(null);
    }

    public boolean authenticate(String username, String password) {
        return false;
    }
}
