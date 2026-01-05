package com.yourorg.telemedicine.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@FeignClient(name = "PATIENT-SERVICE")
public interface PatientClient {
    @GetMapping("/patients/ids")
    List<Long> getAllPatientIds();
}

