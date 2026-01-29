package com.amalitech.SpringBootBloggingApp.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateUserDetailRequest {
    @Email(message = "Invalid Email!")
    @NotBlank(message = "Email is required!")
    private String email;

    @NotBlank(message = "Username is required!")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    public UpdateUserDetailRequest() {
    }

    public UpdateUserDetailRequest(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}