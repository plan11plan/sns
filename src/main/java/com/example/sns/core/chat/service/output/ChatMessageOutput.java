package com.example.sns.core.chat.service.output;

import com.example.sns.core.chat.domain.MessageStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ChatMessageOutput {
    private Long id;
    private Long chatRoomId;
    private Long senderId;
    private String content;
    private LocalDateTime sentAt;
    private boolean isRead;
    private MessageStatus status;
}
