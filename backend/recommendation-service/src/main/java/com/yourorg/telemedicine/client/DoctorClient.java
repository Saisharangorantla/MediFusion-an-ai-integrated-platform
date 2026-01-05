package com.yourorg.telemedicine.client;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yourorg.telemedicine.dto.DoctorSummaryDTO;

import java.util.List;

@FeignClient(
     name = "doctor-service",
     url = "${doctor-service.base-url}"   // or use service discovery name if you have Eureka
)
public interface DoctorClient {

 @GetMapping("/doctors/search")
 List<DoctorSummaryDTO> findDoctors(
         @RequestParam String specialization,
         @RequestParam String city
 );
}
