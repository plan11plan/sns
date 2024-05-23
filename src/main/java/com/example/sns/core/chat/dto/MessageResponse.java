package com.example.sns.core.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MessageResponse {
    private Long id;
    private Long chatRoomId;
    private Long senderId;
    private String content;
    private LocalDateTime sentAt;
    private boolean isRead;
    private String status;
}
