package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponseDto;

public interface AuthService {

    AuthResponseDto register(AuthRequest request);

    AuthResponseDto login(AuthRequest request);
}
