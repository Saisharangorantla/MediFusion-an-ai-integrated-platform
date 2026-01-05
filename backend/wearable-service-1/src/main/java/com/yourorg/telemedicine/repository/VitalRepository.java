package com.yourorg.telemedicine.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.yourorg.telemedicine.entity.VitalData;

import java.util.List;

public interface VitalRepository extends JpaRepository<VitalData, Long> {
    List<VitalData> findByPatientId(Long patientId);
    VitalData findTopByPatientIdOrderByRecordedAtDesc(Long patientId);

}
