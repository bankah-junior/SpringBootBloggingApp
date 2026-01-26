package com.amalitech.SpringBootBloggingApp.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }
}

