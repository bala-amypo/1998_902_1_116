package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository repo;
    private final JwtTokenProvider tokenProvider;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthServiceImpl(UserRepository repo, JwtTokenProvider tokenProvider) {
        this.repo = repo;
        this.tokenProvider = tokenProvider;
    }

    public AuthResponseDto login(AuthRequestDto dto) {
        User user = repo.findByEmail(dto.getEmail()).orElseThrow();
        if (!encoder.matches(dto.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid credentials");

        return new AuthResponseDto(tokenProvider.generateToken(user.getEmail()));
    }
}
