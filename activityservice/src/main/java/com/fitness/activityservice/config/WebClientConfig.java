package com.fitness.activityservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean //This method produces a bean that should be managed by Spring’s IoC container.
    @LoadBalanced //allows webclient to resolve the service name via eureka
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }

    @Bean
    public WebClient userServiceWebClient(WebClient.Builder webclientBuilder){
        return webclientBuilder
                .baseUrl("http://USER-SERVICE")
                .build();
    }
}
