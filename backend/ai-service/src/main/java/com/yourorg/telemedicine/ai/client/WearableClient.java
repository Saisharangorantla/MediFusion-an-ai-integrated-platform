package com.yourorg.telemedicine.ai.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yourorg.telemedicine.ai.dto.VitalReadingDto;

@FeignClient(
    name = "WEARABLE-SERVICE",
    path = "/wearable"
)
public interface WearableClient {

    @GetMapping("/latest/{patientId}")
    VitalReadingDto getLatestVitals(@PathVariable Long patientId);
}
