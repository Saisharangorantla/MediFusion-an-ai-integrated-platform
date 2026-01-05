package com.yourorg.telemedicine.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "health_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Store only the ID because Patient entity is NOT inside this microservice
    private Long patientId;

    private String description;
    private String diagnosis;

    private LocalDateTime createdAt;
}
