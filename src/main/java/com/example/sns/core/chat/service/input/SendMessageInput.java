package com.example.sns.core.chat.service.input;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SendMessageInput {
    private Long chatRoomId;
    private Long senderId;
    private String content;

    @Builder
    public SendMessageInput(Long chatRoomId, Long senderId, String content) {
        this.chatRoomId = chatRoomId;
        this.senderId = senderId;
        this.content = content;
    }
}
