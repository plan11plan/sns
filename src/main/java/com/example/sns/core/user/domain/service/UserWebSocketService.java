package com.example.sns.core.user.domain.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserWebSocketService {
    private final SimpMessagingTemplate messagingTemplate;

    public void notifyUserStatusChange(Long userId, String status) {
        messagingTemplate.convertAndSend("/topic/user-status", new UserStatusMessage(userId, status));
    }

    @Getter
    private static class UserStatusMessage {
        private Long userId;
        private String status;

        public UserStatusMessage(Long userId, String status) {
            this.userId = userId;
            this.status = status;
        }

        // getters and setters
    }
}