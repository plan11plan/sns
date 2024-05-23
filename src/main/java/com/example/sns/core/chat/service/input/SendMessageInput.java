package com.example.sns.core.chat.service.input;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SendMessageInput {
    private Long chatRoomId;
    private Long senderId;
    private String content;
}
