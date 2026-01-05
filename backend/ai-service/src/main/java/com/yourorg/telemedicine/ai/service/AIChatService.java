package com.yourorg.telemedicine.ai.service;

public interface AIChatService {
	String processChat(Long patientId, String userMessage);
	
}
