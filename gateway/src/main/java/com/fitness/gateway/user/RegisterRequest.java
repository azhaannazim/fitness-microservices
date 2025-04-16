package com.fitness.gateway.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "email is required")
    private String email;
    private String keycloakId;
    private String firstName;
    private String lastName;
    private String password;
}
