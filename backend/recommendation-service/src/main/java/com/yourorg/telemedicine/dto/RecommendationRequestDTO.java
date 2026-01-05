package com.yourorg.telemedicine.dto;

import lombok.Data;

@Data
public class RecommendationRequestDTO {
    private String specialization;
    private String city;
    private Long patientId;
    private Integer limit;

    // getters and setters
}
