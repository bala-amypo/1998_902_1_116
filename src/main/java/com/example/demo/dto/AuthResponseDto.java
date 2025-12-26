package com.example.demo.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String token;
    private Long userId;
    private String email;
    private String role;
}