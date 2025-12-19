package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository repository;
    private final JwtTokenProvider tokenProvider;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthController(UserRepository repository, JwtTokenProvider tokenProvider) {
        this.repository = repository;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/register")
    public String register(@RequestBody AuthRequestDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole("USER");
        repository.save(user);
        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto dto) {
        User user = repository.findByEmail(dto.getEmail()).orElseThrow();
        if (!encoder.matches(dto.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid Credentials");

        String token = tokenProvider.generateToken(user.getEmail());
        return new AuthResponseDto(token);
    }
}
