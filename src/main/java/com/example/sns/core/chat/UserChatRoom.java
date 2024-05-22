package com.example.sns.core.chat;

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
                .userId(userId)
                .chatRoomId(chatRoomId)
                .build();
    }
    public Long getUserChatRoomIdValue(){
        return this.id != null ? this.id.getValue() : null;

    }
}