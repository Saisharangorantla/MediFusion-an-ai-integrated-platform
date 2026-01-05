package com.yourorg.telemedicine.service;

import java.util.List;

import com.yourorg.telemedicine.dto.PatientCreateRequest;
import com.yourorg.telemedicine.dto.PatientDTO;
import com.yourorg.telemedicine.entity.Patient;

public interface PatientService {
	
	
	PatientDTO getById(Long id);
	List<PatientDTO> listAll();
	PatientDTO getByUserId(Long userId);
	void save(PatientCreateRequest request);
	
}
