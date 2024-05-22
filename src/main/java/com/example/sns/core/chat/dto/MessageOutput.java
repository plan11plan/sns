package com.example.sns.core.chat.dto;

import com.example.sns.core.chat.Message;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;

@Getter
public class MessageOutput {
    private final UUID id;
    private final UUID chatRoomId;
    private final Long senderId;
    private final String content;
    private final LocalDateTime sentAt;
    private final boolean isRead;

    public MessageOutput(UUID id, UUID chatRoomId, Long senderId, String content, LocalDateTime sentAt, boolean isRead) {
        this.id = id;
        this.chatRoomId = chatRoomId;
        this.senderId = senderId;
        this.content = content;
        this.sentAt = sentAt;
        this.isRead = isRead;
    }

    public static MessageOutput from(Message message) {
        return new MessageOutput(message.getId().getValue(), message.getChatRoomId().getValue(), message.getSenderId().getValue(), message.getContent(), message.getSentAt(), message.isRead());
    }
}
