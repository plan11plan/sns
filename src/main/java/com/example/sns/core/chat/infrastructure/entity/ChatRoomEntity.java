package com.example.sns.core.chat.infrastructure.entity;

import com.example.sns.core.chat.domain.ChatRoom;
import com.example.sns.core.chat.domain.ChatRoomId;
import com.example.sns.core.chat.domain.ChatUserId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "chat_rooms")
public class ChatRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId1;
    private Long userId2;


    public static ChatRoomEntity from(ChatRoom chatRoom) {
        ChatRoomEntity entity = new ChatRoomEntity();
        entity.id = chatRoom.getChatRoomIdValue();
        entity.userId1 = chatRoom.getUserId1Value();
        entity.userId2 = chatRoom.getUserId2Value();
        return entity;
    }

    public ChatRoom toModel() {
        return ChatRoom.builder()
                .id(new ChatRoomId(id))
                .userId1(new ChatUserId(userId1))
                .userId2(new ChatUserId(userId2))
                .build();
    }
}

