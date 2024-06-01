package com.example.sns.core.chat.domain.service.output;

import com.example.sns.core.chat.domain.ChatMessage;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatMessageOutput {
    private Long id;
    private Long chatRoomId;
    private Long senderId;
    private String content;
    private LocalDateTime sentAt;
    private boolean isRead;
    private String status;

    @Builder
    public ChatMessageOutput(Long id, Long chatRoomId, Long senderId, String content, LocalDateTime sentAt,
                             boolean isRead,
                             String status) {
        this.id = id;
        this.chatRoomId = chatRoomId;
        this.senderId = senderId;
        this.content = content;
        this.sentAt = sentAt;
        this.isRead = isRead;
        this.status = status;
    }

    public static ChatMessageOutput from(ChatMessage chatMessage) {
        return ChatMessageOutput.builder()
                .id(chatMessage.getChatMessageIdValue())
                .chatRoomId(chatMessage.getChatRoomIdValue())
                .senderId(chatMessage.getSenderIdValue())
                .content(chatMessage.getContentValue())
                .sentAt(chatMessage.getSentAt())
                .isRead(chatMessage.isRead())
                .status(chatMessage.getStatusValue())
                .build();
    }
}
