package com.yourorg.telemedicine.ai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yourorg.telemedicine.ai.entity.ChatMessage;

public interface ChatMessageRepository
extends JpaRepository<ChatMessage, Long> {

List<ChatMessage> findTop10ByPatientIdOrderByTimestampDesc(Long patientId);
}

