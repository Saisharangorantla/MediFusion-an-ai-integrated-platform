package com.yourorg.telemedicine.videocall.controller;

import com.yourorg.telemedicine.videocall.entity.SignalMessage;
import com.yourorg.telemedicine.videocall.service.VideoCallService;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.web.bind.annotation.*;

@RestController
public class VideoCallController {

    private final VideoCallService videoCallService;

    public VideoCallController(VideoCallService videoCallService) {
        this.videoCallService = videoCallService;
    }

    @MessageMapping("/call.signal/{appointmentId}")
    @SendTo("/topic/call/{appointmentId}")
    public SignalMessage signal(
            @DestinationVariable Long appointmentId,
            SignalMessage message) {

        // Validate whether doctor/patient can join call
        if (!videoCallService.canJoinCall(appointmentId)) {
            throw new RuntimeException("Video call not allowed for this appointment");
        }

        return message; // WebRTC offer / answer / ICE
    }
}
