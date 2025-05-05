package org.example.comicon.controller;

import org.example.comicon.dto.AuthRequest;
import org.example.comicon.security.JwtUtil;
import org.example.comicon.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthService authService;

    public AuthController(JwtUtil jwtUtil, AuthService authService) {
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        boolean success = authService.register(request.getUsername(), request.getPassword());
        if (success) return "User registered successfully";
        return "Username already exists";
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        boolean authenticated = authService.authenticate(request.getUsername(), request.getPassword());
        if (!authenticated) throw new RuntimeException("Invalid credentials");
        return "Bearer " + jwtUtil.generateToken(request.getUsername());
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshToken(@RequestBody String oldToken) {
        if (jwtUtil.isTokenValid(oldToken)) {
            String username = jwtUtil.extractUsername(oldToken);
            String newToken = jwtUtil.generateToken(username);
            return ResponseEntity.ok("Bearer " + newToken);
        }
        return ResponseEntity.badRequest().body("Invalid refresh token");
    }
}
