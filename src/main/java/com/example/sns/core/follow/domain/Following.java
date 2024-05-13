package com.example.sns.core.follow.domain;

import lombok.Getter;
@Getter
public class Following {
    private final Long id;

    private Following(Long id) {
        this.id = id;
    }

    public static Following fromUserId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid ID for Following");
        }
        return new Following(userId);
    }

    public static Following fromId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid ID for Following");
        }
        return new Following(userId);
    }
}
