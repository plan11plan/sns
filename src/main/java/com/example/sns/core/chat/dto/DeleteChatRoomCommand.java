package com.example.sns.core.chat.dto;

import java.util.UUID;
import lombok.Getter;

@Getter
public class DeleteChatRoomCommand {
    private final UUID chatRoomId;

    public DeleteChatRoomCommand(UUID chatRoomId) {
        this.chatRoomId = chatRoomId;
    }
}

