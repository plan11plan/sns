package com.example.sns.core.post.domain.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentId {
    private final Long value;

    @Builder
    public CommentId(Long value) {
        this.value = value;
    }

    public static CommentId of(Long value){
        return CommentId.builder()
                .value(value)
                .build();
    }
}