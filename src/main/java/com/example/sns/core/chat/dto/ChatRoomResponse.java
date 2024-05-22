package com.example.sns.core.chat.dto;


import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ChatRoomResponse {
    private final Long id;
    private final Long userId1;
    private final Long userId2;
    private final LocalDateTime createdAt;

    public ChatRoomResponse(Long id, Long userId1, Long userId2, LocalDateTime createdAt) {
        this.id = id;
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.createdAt = createdAt;
    }
}
