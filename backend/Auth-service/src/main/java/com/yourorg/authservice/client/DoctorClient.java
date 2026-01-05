package com.yourorg.authservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.yourorg.telemedicine.dto.DoctorCreateRequest;

@FeignClient(name = "DOCTOR-SERVICE")
public interface DoctorClient {

    @PostMapping("/doctors/create")
    void createDoctor(@RequestBody DoctorCreateRequest request);
}

