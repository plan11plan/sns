package com.example.sns.core.follow.service.dto.request;


import lombok.Builder;
import lombok.Getter;

@Getter
public class SenderDto {
    Long senderId;
    @Builder

    public SenderDto(Long senderId) {
        this.senderId = senderId;
    }
}
