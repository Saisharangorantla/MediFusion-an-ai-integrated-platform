package com.yourorg.telemedicine.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AppointmentDTO {

    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentTime;
    private String status;
    private String notes;
    private LocalDateTime createdAt;

    // display-only fields
    private String doctorName;
    private String patientName;
    private String specialty;
}
