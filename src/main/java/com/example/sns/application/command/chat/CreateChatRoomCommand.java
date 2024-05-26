package com.example.sns.application.command.chat;

import lombok.Data;

@Data
public class CreateChatRoomCommand {
    private Long userId1;
    private Long userId2;
}
