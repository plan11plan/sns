package com.example.sns.core.chat.dto;

import lombok.Getter;

@Getter
public class MarkMessageAsReadInput {
    private final Long messageId;

    public MarkMessageAsReadInput(Long messageId) {
        this.messageId = messageId;
    }


}