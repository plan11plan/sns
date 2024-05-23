package com.example.sns.core.chat.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatRoomResponse {
    private Long id;
    private Long userId1;
    private Long userId2;
    private LocalDateTime createdAt;
}