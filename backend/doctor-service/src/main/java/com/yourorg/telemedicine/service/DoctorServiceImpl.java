package com.yourorg.telemedicine.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.yourorg.telemedicine.dto.DoctorCreateRequest;
import com.yourorg.telemedicine.dto.DoctorDTO;
import com.yourorg.telemedicine.dto.MessageDto;
import com.yourorg.telemedicine.entity.Doctor;
import com.yourorg.telemedicine.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements Doctorservice {
	@Autowired
    private  DoctorRepository repo;
	@Autowired
	private SmsService k;
	@Autowired
	private KafkaTemplate<String, String> template;
   
//
//   public DoctorDTO create(DoctorDTO dto) {
//       Doctor d = new Doctor();
//       BeanUtils.copyProperties(dto, d);
//       d.setUserId(dto.getUserId());
//       Doctor savedDoctor = repo.save(d);
//       
//       // Convert saved Doctor entity back to DoctorDTO
//       DoctorDTO response = new DoctorDTO();
//       BeanUtils.copyProperties(savedDoctor, response);
//       
//       // Optional: Create message DTO for Kafka notification (if needed)
//       MessageDto msgDto = new MessageDto();
//       msgDto.setId(savedDoctor.getId());
//       msgDto.setName(dto.getName());
//       msgDto.setEmail(dto.getEmail());
//       msgDto.setSpecialization(dto.getSpecialization());
//       msgDto.setExperienceYears(dto.getExperienceYears());
//       msgDto.setMsg("doctor added Successfully");
//       // TODO: Send msgDto via Kafka if needed: template.send("doctor-topic", msgDto);
//       
//       return response;
//   }
//    


    public DoctorDTO getById(Long id) {
        Doctor d = repo.findById(id).orElseThrow();
        var dto = new DoctorDTO();
        dto.setId(d.getId()); 
        dto.setFullName(d.getFullName()); 
        dto.setGender(d.getGender());
        dto.setSpecialization(d.getSpecialization());
        dto.setContact(d.getContact()); 
        dto.setEmail(d.getEmail()); 
        // Note: rating is not included in DTO - calculated from patient reviews
        dto.setExperienceYears(d.getExperienceYears());
        dto.setQualification(d.getQualification());
        dto.setHospital(d.getHospital());
        dto.setConsultationFee(d.getConsultationFee());
        dto.setAddress(d.getAddress());
        dto.setUserId(d.getUserId());
        return dto;
    }

    public List<DoctorDTO> listAll() {
        return repo.findAll().stream().map(d -> {
            var dto = new DoctorDTO();
            dto.setId(d.getId()); 
            dto.setFullName(d.getFullName()); 
            dto.setGender(d.getGender());
            dto.setSpecialization(d.getSpecialization());
            dto.setContact(d.getContact()); 
            dto.setEmail(d.getEmail()); 
            // Note: rating is not included in DTO - calculated from patient reviews
            dto.setExperienceYears(d.getExperienceYears());
            dto.setQualification(d.getQualification());
            dto.setHospital(d.getHospital());
            dto.setConsultationFee(d.getConsultationFee());
            dto.setAddress(d.getAddress());
            dto.setUserId(d.getUserId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public DoctorDTO getByUserId(Long userId) {
        Doctor d = repo.findByUserId(userId).orElseThrow(() -> new RuntimeException("Doctor not found for user ID: " + userId));
        var dto = new DoctorDTO();
        dto.setId(d.getId()); 
        dto.setFullName(d.getFullName()); 
        dto.setGender(d.getGender());
        dto.setSpecialization(d.getSpecialization());
        dto.setContact(d.getContact()); 
        dto.setEmail(d.getEmail()); 
        // Note: rating is not included in DTO - calculated from patient reviews
        dto.setExperienceYears(d.getExperienceYears());
        dto.setQualification(d.getQualification());
        dto.setHospital(d.getHospital());
        dto.setConsultationFee(d.getConsultationFee());
        dto.setAddress(d.getAddress());
        dto.setUserId(d.getUserId());
        return dto;
    }
//    public void notifyDoctor(Long doctorid,Long patientId, String message) {
//    	
//    	 Doctor doctor = repo.findById(doctorid)
//                 .orElseThrow(() ->
//                         new RuntimeException("Doctor not found with id " + doctorid));
//
//        
//        String doctorPhone = doctor.getContact();
//
//        String smsMessage =
//        		 "🚨 HEALTH ALERT 🚨\n"
//        	              + "Doctor: " + doctor.getName() + "\n"
//        	              + "Patient ID: " + patientId + "\n"
//        	              + message;
//
//        k.sendSms(doctorPhone, smsMessage);
//    }
    public void save(DoctorCreateRequest req) {

        Doctor doctor = new Doctor();

        doctor.setFullName(req.getFullName());
        doctor.setGender(req.getGender());
        doctor.setSpecialization(req.getSpecialization());
        doctor.setEmail(req.getEmail());
        doctor.setContact(req.getContact());
        doctor.setExperienceYears(req.getExperienceYears());
        doctor.setQualification(req.getQualification());
        doctor.setHospital(req.getHospital());
        doctor.setConsultationFee(req.getConsultationFee());
        doctor.setAddress(req.getAddress());
        doctor.setUserId(req.getUserId());

        repo.save(doctor);   // 🔥 THIS LINE
    }



	
}
