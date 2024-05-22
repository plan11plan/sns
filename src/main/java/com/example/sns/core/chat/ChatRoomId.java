package com.example.sns.core.chat;

import java.util.UUID;
import lombok.Getter;

@Getter
public class ChatRoomId {
    private final UUID value;

    public ChatRoomId(UUID value) {
        this.value = value;
    }
}
