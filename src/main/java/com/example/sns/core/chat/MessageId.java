package com.example.sns.core.chat;

import java.util.UUID;
import lombok.Getter;

@Getter
public class MessageId {
    private final UUID value;

    public MessageId(UUID value) {
        this.value = value;
    }
}

