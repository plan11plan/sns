package com.example.sns.core.post.exception;

import com.example.sns.core.common.exception.SnsException;

public class PostNotFoundException extends SnsException{
    private static final String MESSAGE = "포스트를 찾을 수 없습니다.";

    public PostNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public String getStatusCode() {
        return "400";
    }
}
