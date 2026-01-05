package com.yourorg.telemedicine.dto;

import lombok.Data;




import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PatientDTO {

    private Long id;

    @NotBlank(message = "Full name is required")
    private String fullName;   // ✅ use camelCase (important)

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Contact number is required")
    private String contact;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    @Positive(message = "Age must be a positive number")
    private Integer age;

    @NotNull(message = "UserId is required")
    private Long userId;
}
