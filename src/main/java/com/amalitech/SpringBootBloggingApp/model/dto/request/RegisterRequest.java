package com.amalitech.SpringBootBloggingApp.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @Email(message = "Invalid Email!")
    @NotBlank(message = "Email is required!")
    private String email;

    @NotBlank(message = "Username is required!")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 8, message = "Password must be exactly 8 characters")
    private String password;

    public RegisterRequest() {
    }

    public RegisterRequest(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}