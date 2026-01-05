package com.yourorg.telemedicine.service;

import java.util.List;

import com.yourorg.telemedicine.dto.HealthRecordDTO;

public interface HealthRecordService {
    HealthRecordDTO create(HealthRecordDTO dto) throws Exception;
    HealthRecordDTO update(Long recordId, HealthRecordDTO dto) throws Exception;
    List<HealthRecordDTO> getRecordsByPatientId(Long patientId);
}
