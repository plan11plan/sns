package com.example.sns.core.post.domain.entity;


import lombok.Builder;
import lombok.Getter;

@Getter
public class PostId {
    private final Long value;

    @Builder
    public PostId(Long value) {
        this.value = value;
    }

    public static PostId of(Long value){
        return PostId.builder()
                .value(value)
                .build();
    }

}
