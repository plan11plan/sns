package com.example.sns.core.post.domain.entity;


import lombok.Getter;

@Getter
public class PostId {
    private final Long value;

    public PostId(Long id) {
        this.value = id;
    }

}
