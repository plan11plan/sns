package com.example.sns.core.follow.domain;

import lombok.Getter;
@Getter
public class Following {
    private final Long id;

    private Following(Long id) {
        this.id = id;
    }

    public static Following fromId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID for Following");
        }
        return new Following(id);
    }
}
