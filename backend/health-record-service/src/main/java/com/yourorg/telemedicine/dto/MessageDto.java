package com.yourorg.telemedicine.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MessageDto implements Serializable{
	    private Long id;
	    private Long patientId;
	    private String description;
	    private String diagnosis;
	    private LocalDateTime createdAt;
	}

