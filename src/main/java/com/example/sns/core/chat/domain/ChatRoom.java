package com.example.sns.core.chat.domain;

import com.example.sns.core.chat.domain.request.ChatRoomCreate;
import java.time.LocalDateTime;
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

    public static ChatRoom create(ChatRoomCreate chatRoomCreate, LocalDateTime createdAt) {
        return ChatRoom.builder()
                .userId1(chatRoomCreate.getUserId1())
                .userId2(chatRoomCreate.getUserId2())
                .createdAt(createdAt)
                .build();
    }
    public Long getChatRoomIdValue(){
        return this.id != null ? this.id.getValue() : null;

    }
    public Long getUserId1Value(){
        return userId1.getValue();
    }
    public Long getUserId2Value(){
        return userId2.getValue();
    }

}