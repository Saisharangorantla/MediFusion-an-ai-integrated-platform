package com.yourorg.telemedicine.dto;

import lombok.Data;

@Data
public class DoctorDto {
    private Long id;
    private String name;
    private String specialization;
    private Double consultationFee;
    // getters + setters
}
