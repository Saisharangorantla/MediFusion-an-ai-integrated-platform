package com.yourorg.telemedicine.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HealthRecordDTO {
    private Long id;
    private Long patientId;
    private String description;
    private String diagnosis;
    private LocalDateTime createdAt;
}
