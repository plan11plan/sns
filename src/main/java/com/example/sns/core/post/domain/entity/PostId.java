package com.example.sns.core.post.domain.entity;


import lombok.Getter;

@Getter
public class PostId {
    private final Long id;

    public PostId(Long id) {
        this.id = id;
    }

}
