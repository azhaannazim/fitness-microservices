package com.fitness.userservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "email is required")
    private String email;
    private String keycloakId;
    private String firstName;
    private String lastName;
}


//benefits of DTO(data transfer object):

//DTO lets you control exactly what the API response or request contains.

//Your API’s input and output structure is separate from your persistence structure.
//This lets you modify your database schema without affecting your API’s clients.

//You can apply validation annotations directly to DTOs (like @NotEmpty) without tying them to your persistence entities.

//DTOs enable you to select only the necessary fields.
//This avoids over-fetching or over-exposing data, reducing network usage.