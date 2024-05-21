package com.example.sns.core.user.domain.entity;

import lombok.Getter;

@Getter
public class UserId {
    private final Long value;

    public UserId(Long value) {
        this.value = value;
    }
}
