package com.yourorg.telemedicine.alert.repo;


import com.yourorg.telemedicine.alert.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByPatientIdOrderByCreatedAtDesc(Long patientId);
}
