package com.example.sns.core.chat;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserChatRoom {
    private final UserChatRoomId id;
    private final ChatUserId userId;
    private final ChatRoomId chatRoomId;

    @Builder
    public UserChatRoom(UserChatRoomId id, ChatUserId userId, ChatRoomId chatRoomId) {
        this.id = id;
        this.userId = userId;
        this.chatRoomId = chatRoomId;
    }

    public static UserChatRoom create(ChatUserId userId, ChatRoomId chatRoomId) {
        return UserChatRoom.builder()
                .id(new UserChatRoomId(UUID.randomUUID()))
                .userId(userId)
                .chatRoomId(chatRoomId)
                .build();
    }
}