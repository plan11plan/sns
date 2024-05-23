package com.example.sns.core.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SendMessageCommand {
    private Long senderId;
    private Long receiverId;
    private String content;
}
