package com.example.sns.core.chat.repository.mapper;

import com.example.sns.core.chat.domain.ChatRoom;
import com.example.sns.core.chat.domain.ChatRoomId;
import com.example.sns.core.chat.domain.ChatUserId;
import com.example.sns.core.chat.repository.entity.ChatRoomEntity;

public class ChatRoomMapper {
    public static ChatRoomEntity toEntity(ChatRoom chatRoom) {
        return ChatRoomEntity.builder()
                .id(chatRoom.getId() != null ? chatRoom.getId().getValue() : null)
                .userId1(chatRoom.getUserId1().getValue())
                .userId2(chatRoom.getUserId2().getValue())
                .build();
    }

    public static ChatRoom toDomain(ChatRoomEntity entity) {
        return ChatRoom.builder()
                .id(new ChatRoomId(entity.getId()))
                .userId1(new ChatUserId(entity.getUserId1()))
                .userId2(new ChatUserId(entity.getUserId2()))
                .build();
    }
}
