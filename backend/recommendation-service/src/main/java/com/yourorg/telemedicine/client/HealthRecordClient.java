package com.yourorg.telemedicine.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yourorg.telemedicine.dto.HealthRecordDTO;

//client/HealthRecordClient.java
@FeignClient(name = "health-record-service", url = "${health-record-service.base-url}")
public interface HealthRecordClient {

 @GetMapping("/records/patient/{patientId}")
 List<HealthRecordDTO> getRecordsForPatient(@PathVariable("patientId") Long patientId);
}
