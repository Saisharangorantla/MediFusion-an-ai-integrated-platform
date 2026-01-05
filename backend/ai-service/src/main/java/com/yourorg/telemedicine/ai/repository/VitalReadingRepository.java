package com.yourorg.telemedicine.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yourorg.telemedicine.ai.entity.VitalReading;

public interface VitalReadingRepository
extends JpaRepository<VitalReading, Long> {

VitalReading findTop1ByPatientIdOrderByTimestampDesc(Long patientId);
}

