package com.example.sns.core.chat.domain;

import lombok.Getter;

@Getter
public class MessageId {
    private final Long value;

    public MessageId(Long value) {
        this.value = value;
    }

}


