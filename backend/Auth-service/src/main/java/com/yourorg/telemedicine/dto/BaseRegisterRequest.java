package com.yourorg.telemedicine.dto;

import lombok.Data;

@Data
public class BaseRegisterRequest {
	private Long userId;

    private String username;
    private String password;
    private String email;
    private String role;
    private String fullName;
}

