package com.example.sns.core.follow.domain;

import lombok.Getter;

@Getter
public class FollowId {
    private final Long value;

    public FollowId(Long value) {
        this.value = value;
    }
}
