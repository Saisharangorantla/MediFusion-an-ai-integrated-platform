package com.yourorg.telemedicine.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DoctorRegisterRequest {

    // common auth fields
    @NotBlank(message = "username is required")
    private String username;
    
    @NotBlank(message = "password is required")
    private String password;
    
    @NotBlank(message = "email is required")
    private String email;
    
    @NotBlank(message = "fullName is required")
    private String fullName;
    
    @NotBlank(message = "gender is required")
    private String gender;
    
    @NotBlank(message = "contact is required")
    private String contact;
    @Column(nullable = false)
    private String address; 

    // doctor-specific fields
    @Column(nullable = false)
    private String specialization;
    @Column(nullable = false)
    private Integer experienceYears;
    @Column(nullable = false)
    private String qualification;
    @Column(nullable = false)
    private String hospital;
    @Column(nullable = false)
    private Double consultationFee;
}
