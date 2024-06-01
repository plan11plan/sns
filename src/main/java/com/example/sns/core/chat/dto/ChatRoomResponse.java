package com.example.sns.core.chat.dto;

import com.example.sns.core.chat.domain.service.output.ChatRoomOutput;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatRoomResponse {
    private Long id;
    private Long userId1;
    private Long userId2;
    private LocalDateTime createdAt;

    public static ChatRoomResponse from(ChatRoomOutput output) {
        return ChatRoomResponse.builder()
                .id(output.getId())
                .userId1(output.getUserId1())
                .userId2(output.getUserId2())
                .createdAt(output.getCreatedAt())
                .build();
    }
}
