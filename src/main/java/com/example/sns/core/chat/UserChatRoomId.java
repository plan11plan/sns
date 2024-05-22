package com.example.sns.core.chat;

import lombok.Getter;

@Getter
public class UserChatRoomId {
    private final Long value;

    public UserChatRoomId(Long value) {
        this.value = value;
    }
}

