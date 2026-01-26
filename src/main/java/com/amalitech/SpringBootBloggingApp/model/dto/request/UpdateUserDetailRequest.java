package com.amalitech.SpringBootBloggingApp.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UpdateUserDetailRequest {
    @Email(message = "Invalid Email!")
    @NotBlank(message = "Email is required!")
    private String email;

    @NotBlank(message = "Username is required!")
    private String username;

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}