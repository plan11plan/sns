package com.example.sns.core.chat.dto;

import com.example.sns.core.chat.ChatRoom;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;

@Getter
public class ChatRoomOutput {
    private final UUID id;
    private final Long userId1;
    private final Long userId2;
    private final LocalDateTime createdAt;

    public ChatRoomOutput(UUID id, Long userId1, Long userId2, LocalDateTime createdAt) {
        this.id = id;
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.createdAt = createdAt;
    }

    public static ChatRoomOutput from(ChatRoom chatRoom) {
        return new ChatRoomOutput(chatRoom.getId().getValue(), chatRoom.getUserId1().getValue(), chatRoom.getUserId2().getValue(), chatRoom.getCreatedAt());
    }
}
