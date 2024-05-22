package com.example.sns.core.chat.dto;

import java.util.UUID;
import lombok.Getter;

@Getter
public class MarkMessageAsReadInput {
    private final UUID messageId;

    public MarkMessageAsReadInput(UUID messageId) {
        this.messageId = messageId;
    }


}