package com.yourorg.telemedicine.Client;

import com.yourorg.telemedicine.dto.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-service", url = "${patient.service.url:http://localhost:8081}")
public interface PatientClient {
    @GetMapping("/patients/{id}")
    PatientDto getPatient(@PathVariable("id") Long id);
}
