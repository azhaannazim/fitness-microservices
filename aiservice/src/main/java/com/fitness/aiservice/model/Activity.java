package com.fitness.aiservice.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class Activity {
    private String id;
    private String userId;
    private Integer caloriesBurned;
    private String type;
    private Integer duration;
    private LocalDateTime startTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Map<String , Object> additionalMetrics;
}
