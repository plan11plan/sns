package com.example.sns.core.chat.domain.service.output;

import com.example.sns.core.chat.domain.ChatRoom;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoomOutput {
    private final Long id;
    private final Long userId1;
    private final Long userId2;
    private final LocalDateTime createdAt;

    @Builder
    public ChatRoomOutput(Long id, Long userId1, Long userId2, LocalDateTime createdAt) {
        this.id = id;
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.createdAt = createdAt;
    }
    public static ChatRoomOutput from(ChatRoom chatRoom){
        return ChatRoomOutput.builder()
                .id(chatRoom.getChatRoomIdValue())
                .userId1(chatRoom.getUserId1().getValue())
                .userId2(chatRoom.getUserId2().getValue())
                .createdAt(chatRoom.getCreatedAt())
                .build();
    }
}
