package com.example.sns.core.chat.dto;

import lombok.Getter;

@Getter
public class SendMessageInput {
    private Long chatRoomId;
    private Long senderId;
    private String content;

    public SendMessageInput(Long chatRoomId, Long senderId, String content) {
        this.chatRoomId = chatRoomId; // Convert to UUID
        this.senderId = senderId; // Convert to UUID
        this.content = content;
    }

    // Getters and setters

}
