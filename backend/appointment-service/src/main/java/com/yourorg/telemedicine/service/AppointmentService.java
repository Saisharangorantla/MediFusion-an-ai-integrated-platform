package com.yourorg.telemedicine.service;

import java.util.List;
import com.yourorg.telemedicine.dto.*;

public interface AppointmentService {

    AppointmentDTO create(CreateAppointmentRequest request);

    AppointmentResponseDto getActiveAppointment(Long patientId);

    List<AppointmentDTO> getAppointmentsByPatientId(Long patientId);
    List<AppointmentDTO> getAppointmentsByDoctorId(Long doctorId);

    AppointmentDTO updateStatus(Long appointmentId, String status);

    void markExpiredAppointmentsAsNotAttended();
}
