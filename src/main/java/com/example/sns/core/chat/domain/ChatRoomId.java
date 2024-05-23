package com.example.sns.core.chat.domain;

import lombok.Getter;

@Getter
public class ChatRoomId {
    private final Long value;

    public ChatRoomId(Long value) {
        this.value = value;
    }



}
