package com.example.sns.core.post.domain.entity;

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

    public Long getValue(){
        return this.value;
    }
}
