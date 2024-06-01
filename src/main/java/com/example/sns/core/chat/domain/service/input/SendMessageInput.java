package com.example.sns.core.chat.domain.service.input;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SendMessageInput {
    private Long senderId;
    private Long receiverId;
    private String content;

    @Builder
    public SendMessageInput(Long senderId, Long receiverId, String content) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }
}
