package com.fitness.aiservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class GeminiService {

    private final WebClient webClient;

    @Value("${value.gemini.url}")
    private String geminiAPIUrl;

    @Value("${value.gemini.key}")
    private String geminiAPIKey;

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String getAnswer(String question){

        Map<String , Object> map = Map.of(
                "contents" , new Object[]{
                        Map.of("parts" , new Object[]{
                                Map.of("text" ,question)
                        })
                }
        );

        String response = webClient.post()
                .uri(geminiAPIUrl + geminiAPIKey)
                .header("Content-Type" ,"application/json")
                .bodyValue(map)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }

}
