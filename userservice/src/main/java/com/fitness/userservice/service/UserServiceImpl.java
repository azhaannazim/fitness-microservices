package com.fitness.userservice.service;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.model.User;
import com.fitness.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse getUserProfile(String userId) {
        User user  = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
//        userResponse.setPassword(user.getPassword());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());

        return userResponse;
    }

    @Override
    public UserResponse register(RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){
            User existingUser = userRepository.findByEmail(request.getEmail());

            return getUserResponse(existingUser);
        }
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setKeycloakId(request.getKeycloakId());
//        user.setPassword(request.getPassword());

        User savedUser = userRepository.save(user);

        return getUserResponse(savedUser);
    }

    private static UserResponse getUserResponse(User existingUser) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(existingUser.getId());
        userResponse.setKeycloakId(existingUser.getKeycloakId());
        userResponse.setEmail(existingUser.getEmail());
//        userResponse.setPassword(existingUser.getPassword());
        userResponse.setFirstName(existingUser.getFirstName());
        userResponse.setLastName(existingUser.getLastName());
        userResponse.setCreatedAt(existingUser.getCreatedAt());
        userResponse.setUpdatedAt(existingUser.getUpdatedAt());
        return userResponse;
    }

    @Override
    public Boolean existsByKeycloakId(String userId) {
        log.info("calling User Validation API for userId : {}", userId );
        return userRepository.existsByKeycloakId(userId);
    }
}
