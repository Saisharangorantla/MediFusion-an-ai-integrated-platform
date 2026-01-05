package com.yourorg.telemedicine.videocall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yourorg.telemedicine.videocall.entity.VideoChatMessage;
import java.util.List;

public interface VideoChatRepository extends JpaRepository<VideoChatMessage, Long> {
    List<VideoChatMessage> findByAppointmentId(Long appointmentId);
}
