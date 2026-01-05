package com.yourorg.telemedicine.alert.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;

import com.yourorg.telemedicine.alert.dto.NotificationRequest;
import com.yourorg.telemedicine.alert.dto.PatientDTO;

@FeignClient(name = "patient-service")
public interface PatientClient {
	
	@GetMapping("/patients/{id}")
    PatientDTO getPatient(@PathVariable Long id);

   
    @PostMapping("/patients/notify")
    void notifyPatient(@RequestBody NotificationRequest request);
}

