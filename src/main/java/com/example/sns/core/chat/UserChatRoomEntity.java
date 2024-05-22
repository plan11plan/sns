package com.example.sns.core.chat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Getter;

@Entity
@Getter
public class UserChatRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long  chatRoomId;

    public static UserChatRoomEntity from(UserChatRoom userChatRoom) {
        UserChatRoomEntity entity = new UserChatRoomEntity();
        entity.id = userChatRoom.getId().getValue();
        entity.userId = userChatRoom.getUserId().getValue();
        entity.chatRoomId = userChatRoom.getChatRoomId().getValue();
        return entity;
    }

    public UserChatRoom toModel() {
        return UserChatRoom.builder()
                .id(new UserChatRoomId(id))
                .userId(new ChatUserId(userId))
                .chatRoomId(new ChatRoomId(chatRoomId))
                .build();
    }
}