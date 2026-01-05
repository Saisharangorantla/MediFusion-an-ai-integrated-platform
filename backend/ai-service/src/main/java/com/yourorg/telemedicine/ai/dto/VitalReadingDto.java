package com.yourorg.telemedicine.ai.dto;



import lombok.Data;
import java.time.LocalDateTime;

@Data
public class VitalReadingDto {
    private Long patientId;
    private int heartRate;
    private int spo2;
    private int systolicBP;
    private int diastolicBP;
    private LocalDateTime recordedAt;
}
