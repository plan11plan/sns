package com.example.sns.core.chat;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoom {
    private final ChatRoomId id;
    private final ChatUserId userId1;
    private final ChatUserId userId2;
    private final LocalDateTime createdAt;

    @Builder
    public ChatRoom(ChatRoomId id, ChatUserId userId1, ChatUserId userId2, LocalDateTime createdAt) {
        this.id = id;
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.createdAt = createdAt;
    }

    public static ChatRoom create(Long userId1, Long userId2, LocalDateTime createdAt) {
        return ChatRoom.builder()
                .id(new ChatRoomId(UUID.randomUUID()))
                .userId1(new ChatUserId(userId1))
                .userId2(new ChatUserId(userId2))
                .createdAt(createdAt)
                .build();
    }
}