package com.example.sns.core.chat.repository.entity;

import com.example.sns.core.chat.domain.ChatMessage;
import com.example.sns.core.chat.domain.ChatRoomId;
import com.example.sns.core.chat.domain.ChatUserId;
import com.example.sns.core.chat.domain.MessageId;
import com.example.sns.core.chat.domain.MessageStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;

@Entity
@Getter
@Table(name = "messages")
public class ChatMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatRoomId;
    private Long senderId;
    private String content;
    private LocalDateTime sentAt;
    private boolean isRead;
    private String status;

    public static ChatMessageEntity from(ChatMessage chatMessage) {
        ChatMessageEntity entity = new ChatMessageEntity();
        entity.chatRoomId = chatMessage.getChatRoomIdValue();
        entity.senderId = chatMessage.getSenderIdValue();
        entity.content = chatMessage.getContentValue();
        entity.sentAt = chatMessage.getSentAt();
        entity.isRead = chatMessage.isRead();
        entity.status = chatMessage.getStatusValue();
        return entity;
    }

    public ChatMessage toModel() {
        return ChatMessage.builder()
                .id(new MessageId(id))
                .chatRoomId(new ChatRoomId(chatRoomId))
                .senderId(new ChatUserId(senderId))
                .content(content)
                .sentAt(sentAt)
                .isRead(isRead)
                .status(MessageStatus.valueOf(status))
                .build();
    }
}
