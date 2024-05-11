package com.example.sns.core.follow.domain;

import lombok.Getter;
@Getter
public class Follower {
    private final Long id;

    private Follower(Long id) {
        this.id = id;
    }

    public static Follower fromId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID for Follower");
        }
        return new Follower(id);
    }
}
