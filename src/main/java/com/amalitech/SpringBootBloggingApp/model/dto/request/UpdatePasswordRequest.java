package com.amalitech.SpringBootBloggingApp.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdatePasswordRequest {
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 8, message = "Password must be exactly 8 characters")
    private String password;

    public String getPassword() {
        return password;
    }
}
