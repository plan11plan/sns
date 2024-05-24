package com.example.sns.core.user.domain.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NicknameHistoryId {
    private final Long value;

    @Builder
    public NicknameHistoryId(Long value) {
        this.value = value;
    }

    public static NicknameHistoryId of(Long value){
        return NicknameHistoryId.builder()
                .value(value)
                .build();
    }
}
