package com.yourorg.telemedicine.dto;



import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CreateAppointmentRequest {

    private Long patientId;
    private Long doctorId;

    // Must match frontend: appointmentTime
    private LocalDateTime appointmentTime;

    private String notes;
}

