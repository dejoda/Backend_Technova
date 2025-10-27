package com.ejemplo.demo.main.controller;

import com.ejemplo.demo.main.JwT.JwtService;
import com.ejemplo.demo.main.User.Role;
import com.ejemplo.demo.main.User.User;
import com.ejemplo.demo.main.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // Buscar usuario en la base de datos
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Generar token JWT
        String token = jwtService.getToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        // Crear nuevo usuario
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.getLastName())
                .country(request.getCountry())
                .role(Role.USER) // si tienes roles
                .build();

        userRepository.save(user);

        // Generar token JWT
        String token = jwtService.getToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();
    }
}
