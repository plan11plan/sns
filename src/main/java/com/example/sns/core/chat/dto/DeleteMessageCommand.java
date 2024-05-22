package com.example.sns.core.chat.dto;

import java.util.UUID;
import lombok.Getter;

@Getter
public class DeleteMessageCommand {
    private final UUID messageId;

    public DeleteMessageCommand(UUID messageId) {
        this.messageId = messageId;
    }
}
