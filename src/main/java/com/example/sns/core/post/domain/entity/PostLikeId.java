package com.example.sns.core.post.domain.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostLikeId {
    private final Long value;


    @Builder
    public PostLikeId(Long value) {
        this.value = value;
    }

    public static PostLikeId of(Long value){
        return PostLikeId.builder()
                .value(value)
                .build();
    }
}
