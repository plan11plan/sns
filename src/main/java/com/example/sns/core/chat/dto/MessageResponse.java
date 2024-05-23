package com.example.sns.core.chat.dto;

import com.example.sns.core.chat.service.output.ChatMessageOutput;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MessageResponse {
    private Long id;
    private Long chatRoomId;
    private Long senderId;
    private String content;
    private LocalDateTime sentAt;
    private boolean isRead;
    private String status;

    public static MessageResponse from(ChatMessageOutput chatMessageOutput) {
        return new MessageResponse(
                chatMessageOutput.getId(),
                chatMessageOutput.getChatRoomId(),
                chatMessageOutput.getSenderId(),
                chatMessageOutput.getContent(),
                chatMessageOutput.getSentAt(),
                chatMessageOutput.isRead(),
                chatMessageOutput.getStatus()
        );
    }
}
