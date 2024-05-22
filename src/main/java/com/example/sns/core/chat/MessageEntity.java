package com.example.sns.core.chat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;

@Entity
@Getter
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID chatRoomId;
    private Long senderId;
    private String content;
    private LocalDateTime sentAt;
    private boolean isRead;

    public static MessageEntity from(Message message) {
        MessageEntity entity = new MessageEntity();
        entity.id = message.getId().getValue();
        entity.chatRoomId = message.getChatRoomId().getValue();
        entity.senderId = message.getSenderId().getValue();
        entity.content = message.getContent();
        entity.sentAt = message.getSentAt();
        entity.isRead = message.isRead();
        return entity;
    }

    public Message toModel() {
        return Message.builder()
                .id(new MessageId(id))
                .chatRoomId(new ChatRoomId(chatRoomId))
                .senderId(new ChatUserId(senderId))
                .content(content)
                .sentAt(sentAt)
                .isRead(isRead)
                .build();
    }
}