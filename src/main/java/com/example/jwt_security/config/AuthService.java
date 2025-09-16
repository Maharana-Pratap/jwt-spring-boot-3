package com.example.jwt_security.config;

import com.example.jwt_security.dto.AuthRequest;
import com.example.jwt_security.dto.AuthResponse;
import com.example.jwt_security.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(AuthRequest request) {
       Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );
        User user = (User) authentication.getPrincipal();
        //var user = authentication.getPrincipal();
        String token = jwtUtil.generateToken(user);
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        return response ;
    }
}