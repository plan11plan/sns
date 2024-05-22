package com.example.sns.core.chat.dto;

import java.util.UUID;
import lombok.Getter;

@Getter
public class DeleteMessageInput {
    private final UUID messageId;

    public DeleteMessageInput(UUID messageId) {
        this.messageId = messageId;
    }
}
