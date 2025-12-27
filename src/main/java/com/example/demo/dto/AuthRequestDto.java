package com.example.demo.dto;

public class AuthRequestDto {

    private String email;
    private String password;

    // ✅ No-arg constructor (REQUIRED)
    public AuthRequestDto() {
    }

    // ✅ Optional constructor (safe to keep)
    public AuthRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // ✅ Getters & Setters (REQUIRED by tests)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
