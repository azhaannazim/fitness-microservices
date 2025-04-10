package com.fitness.userservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "email is required")
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
