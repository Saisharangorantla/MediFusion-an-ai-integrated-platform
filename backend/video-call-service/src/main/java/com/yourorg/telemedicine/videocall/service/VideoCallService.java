package com.yourorg.telemedicine.videocall.service;

import org.springframework.stereotype.Service;

@Service
public class VideoCallService {

    public boolean canJoinCall(Long appointmentId) {
        // Later you can check:
        // - appointment exists
        // - status = CONFIRMED
        // - current time within slot
        return true;
    }
}
