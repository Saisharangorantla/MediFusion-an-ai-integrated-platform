package com.yourorg.telemedicine.ai.dto;

import lombok.Data;

@Data
public class ChatRequestDto {

	private Long patientId;

	private String message;
}
