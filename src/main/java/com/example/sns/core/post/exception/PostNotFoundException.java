package com.example.sns.core.post.exception;

import com.example.sns.common.exception.ResourceNotFoundException;

public class PostNotFoundException extends ResourceNotFoundException {
    private static final String MESSAGE = "포스트를 찾을 수 없습니다.";

    public PostNotFoundException() {
        super(MESSAGE);
    }
}
