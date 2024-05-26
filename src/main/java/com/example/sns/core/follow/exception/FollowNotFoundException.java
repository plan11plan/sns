package com.example.sns.core.follow.exception;

import com.example.sns.common.exception.ResourceNotFoundException;

public class FollowNotFoundException extends ResourceNotFoundException {
    private final static  String MESSAGE = "팔로우를 찾을 수 없습니다.";
    public FollowNotFoundException() {
        super(MESSAGE);
    }

}
