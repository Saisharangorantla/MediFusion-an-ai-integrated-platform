package com.yourorg.telemedicine.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Data
@Table(name = "vital_data")
public class VitalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ REQUIRED to link vitals to patient
    private Long patientId;

    private int heartRate;
    private int spo2;
    private int systolicBP;
    private int diastolicBP;

    private LocalDateTime recordedAt;
}
