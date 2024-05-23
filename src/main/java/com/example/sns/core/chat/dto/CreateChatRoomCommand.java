package com.example.sns.core.chat.dto;

import lombok.Data;

@Data
public class CreateChatRoomCommand {
    private Long userId1;
    private Long userId2;
}
