package com.yourorg.telemedicine.dto;

import lombok.Data;

@Data
public class AppointmentResponseDto {

    private Long doctorId;
    private boolean active;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
