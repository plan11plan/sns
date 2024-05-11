package com.example.sns.core.follow.service.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReceiverDto {
    Long receiverId;

    @Builder
    public ReceiverDto(Long receiverId) {
        this.receiverId = receiverId;
    }
}
