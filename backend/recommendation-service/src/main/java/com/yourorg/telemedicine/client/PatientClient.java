package com.yourorg.telemedicine.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yourorg.telemedicine.dto.PatientDto;

//client/PatientClient.java
@FeignClient(name = "patient-service", url = "${patient-service.base-url}")
public interface PatientClient {

 @GetMapping("/patients/{id}")
 PatientDto getPatient(@PathVariable("id") Long id);
}

