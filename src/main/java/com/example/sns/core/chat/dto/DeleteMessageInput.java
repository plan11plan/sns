package com.example.sns.core.chat.dto;

import lombok.Getter;

@Getter
public class DeleteMessageInput {
    private final Long messageId;

    public DeleteMessageInput(Long messageId) {
        this.messageId = messageId;
    }
}
