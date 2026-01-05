package com.yourorg.telemedicine.controller;

import com.yourorg.telemedicine.dto.HealthRecordDTO;
import com.yourorg.telemedicine.service.HealthRecordService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/records")
public class HealthRecordController {
	@Autowired
    private HealthRecordService service;

	@PreAuthorize("hasRole('DOCTOR')")
    @PostMapping
    public ResponseEntity<HealthRecordDTO> create(@RequestBody HealthRecordDTO dto) throws Exception {
        return ResponseEntity.ok(service.create(dto));
    }
    // NEW: update endpoint so doctor can update patient record
    @PutMapping("/{recordId}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<HealthRecordDTO> update(
            @PathVariable Long recordId,
            @RequestBody HealthRecordDTO dto) throws Exception {

        HealthRecordDTO updated = service.update(recordId, dto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasAnyRole('PATIENT','DOCTOR')")
    public ResponseEntity<List<HealthRecordDTO>> getRecordsByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(service.getRecordsByPatientId(patientId));
    }
}
