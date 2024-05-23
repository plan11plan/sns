package com.example.sns.core.chat.domain;

import lombok.Getter;

@Getter
public class ChatUserId {
    private final Long value;

    public ChatUserId(Long value) {
        this.value = value;
    }

}
