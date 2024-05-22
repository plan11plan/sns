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
public class ChatRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Long userId1;
    private Long userId2;
    private LocalDateTime createdAt;

    public static ChatRoomEntity from(ChatRoom chatRoom) {
        ChatRoomEntity entity = new ChatRoomEntity();
        entity.id = chatRoom.getId().getValue();
        entity.userId1 = chatRoom.getUserId1().getValue();
        entity.userId2 = chatRoom.getUserId2().getValue();
        entity.createdAt = chatRoom.getCreatedAt();
        return entity;
    }

    public ChatRoom toModel() {
        return ChatRoom.builder()
                .id(new ChatRoomId(id))
                .userId1(new ChatUserId(userId1))
                .userId2(new ChatUserId(userId2))
                .createdAt(createdAt)
                .build();
    }
}