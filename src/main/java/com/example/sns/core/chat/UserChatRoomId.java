package com.example.sns.core.chat;

import java.util.UUID;
import lombok.Getter;

@Getter
public class UserChatRoomId {
    private final UUID value;

    public UserChatRoomId(UUID value) {
        this.value = value;
    }
}

