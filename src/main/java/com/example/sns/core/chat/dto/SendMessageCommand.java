package com.example.sns.core.chat.dto;

import lombok.Data;

@Data
public class SendMessageCommand {
    private Long chatRoomId;
    private Long senderId;
    private String content;
}
