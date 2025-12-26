package com.example.demo.dto;

import java.util.Set;

public class UserDto {

    private Long id;
    private String email;
    private String password;
    private Set<String> roles;

    // ✅ No-args constructor (IMPORTANT for test cases)
    public UserDto() {
    }

    // ✅ All-args constructor (optional but safe)
    public UserDto(Long id, String email, String password, Set<String> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    // ✅ Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
