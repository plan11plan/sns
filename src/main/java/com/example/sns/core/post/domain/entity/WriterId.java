package com.example.sns.core.post.domain.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WriterId {
    private final Long value;

    public Long getValue(){
        return this.value;
    }

    @Builder
    public WriterId(long value) {
        this.value = value;
    }

    public static WriterId of(Long value){
        return WriterId.builder()
                .value(value)
                .build();
    }
}
