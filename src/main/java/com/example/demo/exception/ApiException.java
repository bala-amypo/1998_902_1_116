package com.example.demo.exception;

import java.time.LocalDateTime;

public class ApiException {

    private String message;
    private LocalDateTime timestamp;

    public ApiException(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
