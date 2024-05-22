package com.example.sns.core.chat.dto;

import lombok.Getter;

@Getter
public class DeleteChatRoomCommand {
    private final Long chatRoomId;

    public DeleteChatRoomCommand(Long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }
}

