package com.yourorg.telemedicine.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.yourorg.telemedicine.dto.AppointmentDTO;
import com.yourorg.telemedicine.dto.CreateAppointmentRequest;
import com.yourorg.telemedicine.dto.AppointmentResponseDto;

import com.yourorg.telemedicine.service.AppointmentService;


@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService service;

  

    @Autowired
    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    // ✅ CREATE APPOINTMENT (FIXED)
    @PostMapping("/consult")
    public ResponseEntity<AppointmentDTO> create(
            @RequestBody CreateAppointmentRequest request) {

        return ResponseEntity.ok(service.create(request));
    }

    // ✅ GET ACTIVE APPOINTMENT
    @GetMapping("/active/{patientId}")
    public AppointmentResponseDto getActiveAppointment(
            @PathVariable Long patientId) {

        return service.getActiveAppointment(patientId);
    }

 

    // ✅ GET APPOINTMENTS BY PATIENT
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByPatientId(
            @PathVariable Long patientId) {

        return ResponseEntity.ok(service.getAppointmentsByPatientId(patientId));
    }

    // ✅ GET APPOINTMENTS BY DOCTOR
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByDoctorId(
            @PathVariable Long doctorId) {

        return ResponseEntity.ok(service.getAppointmentsByDoctorId(doctorId));
    }

    // ✅ UPDATE STATUS
    @PutMapping("/{id}/status")
    public ResponseEntity<AppointmentDTO> updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {

        return ResponseEntity.ok(
                service.updateStatus(id, request.get("status"))
        );
    }

    // ✅ MARK EXPIRED AS NOT ATTENDED
    @PostMapping("/mark-expired-as-not-attended")
    public void markExpiredAppointmentsAsNotAttended() {
        service.markExpiredAppointmentsAsNotAttended();
    }
}
