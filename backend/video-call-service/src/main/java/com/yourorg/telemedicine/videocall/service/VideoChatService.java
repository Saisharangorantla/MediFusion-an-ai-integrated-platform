package com.yourorg.telemedicine.videocall.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yourorg.telemedicine.videocall.entity.VideoChatMessage;
import com.yourorg.telemedicine.videocall.repository.VideoChatRepository;

@Service
public class VideoChatService {

    private final VideoChatRepository repository;

    public VideoChatService(VideoChatRepository repository) {
        this.repository = repository;
    }

    /**
     * Save a live chat message during video call
     */
    public VideoChatMessage saveMessage(Long appointmentId, VideoChatMessage message) {

        if (appointmentId == null) {
            throw new IllegalArgumentException("Appointment ID is required");
        }

        if (!StringUtils.hasText(message.getMessage())) {
            throw new IllegalArgumentException("Chat message cannot be empty");
        }

        message.setAppointmentId(appointmentId);
        message.setTimestamp(LocalDateTime.now());

        // sender should be DOCTOR or PATIENT (can validate later)
        return repository.save(message);
    }

    /**
     * Fetch chat history for a video call
     */
    public List<VideoChatMessage> getChatHistory(Long appointmentId) {

        if (appointmentId == null) {
            throw new IllegalArgumentException("Appointment ID is required");
        }

        return repository.findByAppointmentId(appointmentId);
    }
}
