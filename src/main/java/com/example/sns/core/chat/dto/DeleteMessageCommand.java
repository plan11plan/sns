package com.example.sns.core.chat.dto;

import lombok.Getter;

@Getter
public class DeleteMessageCommand {
    private final Long messageId;

    public DeleteMessageCommand(Long  messageId) {
        this.messageId = messageId;
    }
}
