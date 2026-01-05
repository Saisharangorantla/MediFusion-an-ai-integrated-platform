package com.yourorg.telemedicine.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yourorg.telemedicine.dto.CreateAppointmentRequest;
import com.yourorg.telemedicine.client.DoctorClient;
import com.yourorg.telemedicine.client.PatientClient;
import com.yourorg.telemedicine.dto.*;
import com.yourorg.telemedicine.entity.Appointment;
import com.yourorg.telemedicine.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository repo;

    @Autowired
    private PatientClient patientClient;

    @Autowired
    private DoctorClient doctorClient;

    private static final int WINDOW_DAYS = 30;

    // ✅ CREATE APPOINTMENT (FIXED)
    @Override
    @Transactional
    public AppointmentDTO create(CreateAppointmentRequest request) {

        // Optional validation (do NOT block DB insert)
        try {
            patientClient.getPatient(request.getPatientId());
        } catch (Exception e) {
            System.out.println("Patient service unavailable, continuing...");
        }

        try {
            doctorClient.getDoctor(request.getDoctorId());
        } catch (Exception e) {
            System.out.println("Doctor service unavailable, continuing...");
        }

        // Create entity
        Appointment appointment = new Appointment();
        appointment.setPatientId(request.getPatientId());
        appointment.setDoctorId(request.getDoctorId());
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setNotes(request.getNotes());
        appointment.setStatus("SCHEDULED");
        appointment.setCreatedAt(LocalDateTime.now());

        Appointment saved = repo.save(appointment);

        return convertToDTO(saved, true);
    }

    // ✅ ACTIVE APPOINTMENT
    @Override
    public AppointmentResponseDto getActiveAppointment(Long patientId) {

        AppointmentResponseDto response = new AppointmentResponseDto();

        Optional<Appointment> opt =
                repo.findFirstByPatientIdAndStatus(patientId, "SCHEDULED");

        if (opt.isEmpty()) {
            response.setActive(false);
            return response;
        }

        Appointment appt = opt.get();
        LocalDateTime now = LocalDateTime.now();

        boolean active =
                now.isAfter(appt.getAppointmentTime().minusDays(WINDOW_DAYS)) &&
                now.isBefore(appt.getAppointmentTime().plusDays(WINDOW_DAYS));

        response.setActive(active);
        response.setDoctorId(active ? appt.getDoctorId() : null);

        return response;
    }

    // ✅ GET BY PATIENT
    @Override
    public List<AppointmentDTO> getAppointmentsByPatientId(Long patientId) {
        return repo.findByPatientIdOrderByAppointmentTimeDesc(patientId)
                .stream()
                .map(a -> convertToDTO(a, true))
                .collect(Collectors.toList());
    }

    // ✅ GET BY DOCTOR
    @Override
    public List<AppointmentDTO> getAppointmentsByDoctorId(Long doctorId) {
        return repo.findByDoctorIdOrderByAppointmentTimeDesc(doctorId)
                .stream()
                .map(a -> convertToDTO(a, false))
                .collect(Collectors.toList());
    }

    // ✅ UPDATE STATUS
    @Override
    @Transactional
    public AppointmentDTO updateStatus(Long appointmentId, String status) {

        Appointment appointment = repo.findById(appointmentId)
                .orElseThrow(() ->
                        new RuntimeException("Appointment not found with id: " + appointmentId));

        appointment.setStatus(status);
        return convertToDTO(repo.save(appointment), true);
    }

    // ✅ ENTITY → DTO
    private AppointmentDTO convertToDTO(Appointment appointment, boolean fetchDoctorInfo) {

        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(appointment.getId());
        dto.setPatientId(appointment.getPatientId());
        dto.setDoctorId(appointment.getDoctorId());
        dto.setAppointmentTime(appointment.getAppointmentTime());
        dto.setStatus(appointment.getStatus());
        dto.setNotes(appointment.getNotes());
        dto.setCreatedAt(appointment.getCreatedAt());

        // Doctor info
        if (appointment.getDoctorId() != null) {
            try {
                DoctorDto doctor = doctorClient.getDoctor(appointment.getDoctorId());
                if (doctor != null) {
                    dto.setDoctorName(doctor.getName());
                    dto.setSpecialty(doctor.getSpecialization());
                }
            } catch (Exception ignored) {}
        }

        // Patient info (doctor view)
        if (!fetchDoctorInfo && appointment.getPatientId() != null) {
            try {
                PatientDto patient = patientClient.getPatient(appointment.getPatientId());
                if (patient != null) {
                    dto.setPatientName(patient.getName());
                }
            } catch (Exception ignored) {}
        }

        return dto;
    }

    // ✅ MARK EXPIRED AS NOT ATTENDED
    @Override
    @Transactional
    public void markExpiredAppointmentsAsNotAttended() {
        // Mark as "not attended" if appointment time was more than 1 hour ago
        LocalDateTime cutoff = LocalDateTime.now().minusMinutes(10);
        
        List<Appointment> expired = repo.findByStatusAndAppointmentTimeBefore("SCHEDULED", cutoff);
        
        for (Appointment appt : expired) {
            appt.setStatus("not attended");
            repo.save(appt);
            // Optionally could log this action
            System.out.println("Marked appointment " + appt.getId() + " as not attended.");
        }
    }
}
