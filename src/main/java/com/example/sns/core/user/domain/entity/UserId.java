package com.example.sns.core.user.domain.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserId {
    private final Long value;

    @Builder
    public UserId(Long value) {
        this.value = value;
    }

    public static UserId of(Long value){
        return UserId.builder()
                .value(value)
                .build();

    }
}
