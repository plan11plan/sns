package com.example.sns.core.chat.repository.entity;

import com.example.sns.core.chat.domain.MessageStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "messages")
@NoArgsConstructor
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatRoomId;
    private Long senderId;
    private String content;
    private LocalDateTime sentAt;
    private boolean isRead;
    private MessageStatus status;

    @Builder
    public MessageEntity(Long id, Long chatRoomId, Long senderId, String content, LocalDateTime sentAt, boolean isRead, MessageStatus status) {
        this.id = id;
        this.chatRoomId = chatRoomId;
        this.senderId = senderId;
        this.content = content;
        this.sentAt = sentAt;
        this.isRead = isRead;
        this.status = status;
    }
}
