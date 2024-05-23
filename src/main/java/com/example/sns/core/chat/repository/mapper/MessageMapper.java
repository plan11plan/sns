package com.example.sns.core.chat.repository.mapper;

import com.example.sns.core.chat.domain.Message;
import com.example.sns.core.chat.domain.MessageId;
import com.example.sns.core.chat.domain.ChatRoomId;
import com.example.sns.core.chat.domain.ChatUserId;
import com.example.sns.core.chat.repository.entity.MessageEntity;

public class MessageMapper {
    public static MessageEntity toEntity(Message message) {
        return MessageEntity.builder()
                .id(message.getId() != null ? message.getId().getValue() : null)
                .chatRoomId(message.getChatRoomId().getValue())
                .senderId(message.getSenderId().getValue())
                .content(message.getContent())
                .sentAt(message.getSentAt())
                .isRead(message.isRead())
                .status(message.getStatus())
                .build();
    }

    public static Message toDomain(MessageEntity entity) {
        return Message.builder()
                .id(new MessageId(entity.getId()))
                .chatRoomId(new ChatRoomId(entity.getChatRoomId()))
                .senderId(new ChatUserId(entity.getSenderId()))
                .content(entity.getContent())
                .sentAt(entity.getSentAt())
                .isRead(entity.isRead())
                .status(entity.getStatus())
                .build();
    }
}
