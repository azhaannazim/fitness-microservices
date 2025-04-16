package com.fitness.userservice.service;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import jakarta.validation.Valid;

public interface UserService {
    UserResponse getUserProfile(String userId);

    UserResponse register(@Valid RegisterRequest request);

    Boolean existsByKeycloakId(String userId);
}
