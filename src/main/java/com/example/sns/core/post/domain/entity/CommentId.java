package com.example.sns.core.post.domain.entity;

import lombok.Getter;

@Getter
public class CommentId {
    private final Long value;

    public CommentId(Long value) {
        this.value = value;
    }
}