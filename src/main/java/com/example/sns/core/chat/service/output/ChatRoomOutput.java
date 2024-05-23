package com.example.sns.core.chat.service.output;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ChatRoomOutput {
    private Long id;
    private Long userId1;
    private Long userId2;
    private LocalDateTime createdAt;
}
