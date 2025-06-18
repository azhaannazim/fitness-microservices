package com.fitness.gateway.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final WebClient userServiceWebClient;

    public Mono<Boolean> validateUser(String userId) {
        log.info("validating user wit id : {}" , userId);
        return userServiceWebClient.get()
                .uri("/api/users/{userId}/validate", userId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                        log.error("User not found: {}", userId);
                        return Mono.just(false);
                    } else {
                        log.error("Unexpected error validating user {}: {}", userId, e.getMessage());
                        return Mono.error(new RuntimeException("Failed to validate user"));
                    }
                });
    }

    public Mono<UserResponse> registerUser(RegisterRequest request) {
        log.info("registering user.... : {}" , request.getEmail());

        return userServiceWebClient.post()
                .uri("/api/users/register")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    log.error("Error during user registration", e);
                    return Mono.error(new RuntimeException(e.getMessage()));
                });
    }
}
