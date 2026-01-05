package com.yourorg.telemedicine.dto;

import lombok.Data;

@Data
public class PatientCreateRequest extends BaseRegisterRequest{

	private String gender;
    private String contact;
    private Integer age;
    private String address;

    // getters and setters
}
