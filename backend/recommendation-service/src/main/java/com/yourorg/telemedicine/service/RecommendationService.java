package com.yourorg.telemedicine.service;


import java.util.List;

import com.yourorg.telemedicine.dto.RecommendationRequestDTO;
import com.yourorg.telemedicine.dto.RecommendedDoctorDTO;

public interface RecommendationService {
 List<RecommendedDoctorDTO> recommendDoctors(RecommendationRequestDTO request);
}
