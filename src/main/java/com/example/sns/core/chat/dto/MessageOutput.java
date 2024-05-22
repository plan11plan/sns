package com.example.sns.core.chat.dto;

import com.example.sns.core.chat.Message;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class MessageOutput {
    private final Long id;
    private final Long chatRoomId;
    private final Long senderId;
    private final String content;
    private final LocalDateTime sentAt;
    private final boolean isRead;

    public MessageOutput(Long id, Long chatRoomId, Long senderId, String content, LocalDateTime sentAt, boolean isRead) {
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
