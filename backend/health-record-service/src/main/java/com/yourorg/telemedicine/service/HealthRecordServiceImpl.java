package com.yourorg.telemedicine.service;

import java.util.List;
import java.util.stream.Collectors;

import com.yourorg.telemedicine.Client.PatientClient;
import com.yourorg.telemedicine.dto.HealthRecordDTO;
import com.yourorg.telemedicine.dto.MessageDto;
import com.yourorg.telemedicine.dto.PatientDto;
import com.yourorg.telemedicine.entity.HealthRecord;

import com.yourorg.telemedicine.repository.HealthRecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class HealthRecordServiceImpl implements HealthRecordService {
	@Autowired
    private HealthRecordRepository repo;
	@Autowired
    private  PatientClient patientClient;
	@Autowired
	private KafkaTemplate<String, String> template;

//    public HealthRecordService(HealthRecordRepository repo, PatientClient patientClient) {
//        this.repo = repo;
//        this.patientClient = patientClient;
//    }

    /**
     * Create a health record. Validates that the patient exists by calling patient-service.
     * Stores only patientId in HealthRecord (no Patient entity).
     * @throws Exception 
     */
    @Transactional
    public HealthRecordDTO create(HealthRecordDTO dto) throws Exception {
        // validate patient exists using remote service
        PatientDto p;
        try {
            p = patientClient.getPatient(dto.getPatientId());
        } catch (Exception e) {
            // treat client errors or connectivity issues as "not found/unavailable"
            throw new Exception("Patient not found or patient-service unavailable");
        }

        if (p == null || p.getId() == null) {
            throw new Exception("Patient not found");
        }

        // build HealthRecord (store only ids)
        HealthRecord hr = new HealthRecord();
        hr.setPatientId(dto.getPatientId());            // <-- important: ID only
        hr.setDescription(dto.getDescription());
        hr.setDiagnosis(dto.getDiagnosis());
        hr.setCreatedAt(LocalDateTime.now());

        var saved = repo.save(hr);

        dto.setId(saved.getId());
        dto.setCreatedAt(saved.getCreatedAt());
        //send to topic
        MessageDto msgDto=new MessageDto();
        msgDto.setId(dto.getId());
        msgDto.setPatientId(dto.getPatientId());
        msgDto.setDescription(dto.getDescription());
        msgDto.setDiagnosis(dto.getDiagnosis());
        msgDto.setCreatedAt(dto.getCreatedAt());
        return dto;
    }
    @Transactional
    public HealthRecordDTO update(Long recordId, HealthRecordDTO dto) throws Exception {
        HealthRecord existing = repo.findById(recordId)
                .orElseThrow(() -> new Exception("Health record not found with id " + recordId));

        // Optionally re‑validate patient (if patientId can change)
        if (dto.getPatientId() != null && !dto.getPatientId().equals(existing.getPatientId())) {
            PatientDto p;
            try {
                p = patientClient.getPatient(dto.getPatientId());
            } catch (Exception e) {
                throw new Exception("Patient not found or patient-service unavailable");
            }
            if (p == null || p.getId() == null) {
                throw new Exception("Patient not found");
            }
            existing.setPatientId(dto.getPatientId());
        }

        // Update allowed fields
        existing.setDescription(dto.getDescription());
        existing.setDiagnosis(dto.getDiagnosis());
        // you can also set updatedAt if you add that column

        HealthRecord saved = repo.save(existing);

        // map back to DTO
        dto.setId(saved.getId());
        dto.setPatientId(saved.getPatientId());
        dto.setCreatedAt(saved.getCreatedAt());
        return dto;
    }

    @Override
    public List<HealthRecordDTO> getRecordsByPatientId(Long patientId) {
        List<HealthRecord> records = repo.findByPatientId(patientId);
        return records.stream().map(record -> {
            HealthRecordDTO dto = new HealthRecordDTO();
            dto.setId(record.getId());
            dto.setPatientId(record.getPatientId());
            dto.setDescription(record.getDescription());
            dto.setDiagnosis(record.getDiagnosis());
            dto.setCreatedAt(record.getCreatedAt());
            return dto;
        }).collect(Collectors.toList());
    }

}
