package com.example.sns.core.chat.dto;

import java.util.UUID;
import lombok.Getter;

@Getter
public class SendMessageCommand {
    private final UUID chatRoomId;
    private final Long senderId;
    private final String content;

    public SendMessageCommand(UUID chatRoomId, Long senderId, String content) {
        this.chatRoomId = chatRoomId;
        this.senderId = senderId;
        this.content = content;
    }
}