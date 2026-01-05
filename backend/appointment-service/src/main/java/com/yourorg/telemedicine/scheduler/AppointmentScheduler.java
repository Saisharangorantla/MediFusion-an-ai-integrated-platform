package com.yourorg.telemedicine.scheduler;

import com.yourorg.telemedicine.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AppointmentScheduler {

    private final AppointmentService service;

    @Autowired
    public AppointmentScheduler(AppointmentService service) {
        this.service = service;
    }

    // Run every 15 minutes
    @Scheduled(cron = "0 0/15 * * * *")
    public void checkExpiredAppointments() {
        System.out.println("Running scheduled check for not attended appointments...");
        service.markExpiredAppointmentsAsNotAttended();
    }
}
