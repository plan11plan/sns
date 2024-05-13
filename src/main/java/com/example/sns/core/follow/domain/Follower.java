package com.example.sns.core.follow.domain;

import lombok.Getter;
@Getter
public class Follower {
    private final Long id;

    private Follower(Long userId) {
        this.id = userId;
    }

    public static Follower fromUserId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid ID for Follower");
        }
        return new Follower(userId);
    }
    public static Follower fromId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid ID for Follower");
        }
        return new Follower(userId);
    }
    public Long getId(){
        return id;
    }
}
