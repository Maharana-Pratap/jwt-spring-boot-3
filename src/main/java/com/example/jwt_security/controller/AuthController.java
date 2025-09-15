package com.example.jwt_security.controller;

import com.example.jwt_security.config.AuthService;
import com.example.jwt_security.config.JwtUtil;
import com.example.jwt_security.dto.AuthRequest;
import com.example.jwt_security.dto.AuthResponse;
import com.example.jwt_security.entity.User;
import com.example.jwt_security.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthUserService authUserService;
    private final AuthenticationManager authenticationManager;
   // private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(Map.of("token", response.getToken()))  ;
        } catch (AuthenticationException e) {
            return ResponseEntity.ok(Map.of("error", "Invalid username or password"));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        return ResponseEntity.ok(authUserService.signup(user));
    }
}