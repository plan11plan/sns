package com.example.sns.core.chat.dto;


import lombok.Getter;

@Getter
public class CreateChatRoomInput {
    private final Long userId1;
    private final Long userId2;

    public CreateChatRoomInput(Long userId1, Long userId2) {
        this.userId1 = userId1;
        this.userId2 = userId2;
    }
}