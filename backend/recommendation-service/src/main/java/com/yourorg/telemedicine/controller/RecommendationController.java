package com.yourorg.telemedicine.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourorg.telemedicine.dto.RecommendationRequestDTO;
import com.yourorg.telemedicine.dto.RecommendedDoctorDTO;
import com.yourorg.telemedicine.service.RecommendationService;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {
@Autowired
 private  RecommendationService recommendationService;



 @GetMapping("//suggested-doctors")
 public ResponseEntity<List<RecommendedDoctorDTO>> recommendDoctors(
		 @RequestBody RecommendationRequestDTO request) {

   

     List<RecommendedDoctorDTO> result = recommendationService.recommendDoctors(request);
     return ResponseEntity.ok(result);
 }
}
