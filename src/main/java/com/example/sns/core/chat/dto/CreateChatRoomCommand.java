package com.example.sns.core.chat.dto;

import lombok.Getter;

@Getter
public class CreateChatRoomCommand {
    private final Long userId1;
    private final Long userId2;

    public CreateChatRoomCommand(Long userId1, Long userId2) {
        this.userId1 = userId1;
        this.userId2 = userId2;
    }
}