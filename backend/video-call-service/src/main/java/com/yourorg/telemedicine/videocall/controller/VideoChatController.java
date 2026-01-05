package com.yourorg.telemedicine.videocall.controller;

import com.yourorg.telemedicine.videocall.entity.VideoChatMessage;
import com.yourorg.telemedicine.videocall.service.VideoChatService;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.stereotype.Controller;

@Controller
public class VideoChatController {

    private final VideoChatService chatService;

    public VideoChatController(VideoChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/chat.send/{appointmentId}")
    @SendTo("/topic/chat/{appointmentId}")
    public VideoChatMessage sendMessage(
            @DestinationVariable Long appointmentId,
            VideoChatMessage message) {

        return chatService.saveMessage(appointmentId, message);
    }
}
