package com.yourorg.telemedicine.ai.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "chat_messages")
@Data
public class ChatMessage {
	
	 public ChatMessage() {
	    }

	public ChatMessage(Long patientId, ChatRole role, String message, LocalDateTime timestamp) {
		super();
		this.patientId = patientId;
		this.role = role;
		this.message = message;
		this.timestamp = timestamp;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;

    @Enumerated(EnumType.STRING)
    private ChatRole role; // USER or AI

    @Column(length = 3000)
    private String message;

    private LocalDateTime timestamp = LocalDateTime.now();

    // getters & setters
}

