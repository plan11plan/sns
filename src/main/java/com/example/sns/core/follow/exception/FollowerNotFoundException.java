package com.example.sns.core.follow.exception;

import com.example.sns.common.exception.ResourceNotFoundException;

public class FollowerNotFoundException extends ResourceNotFoundException {
    private final static  String MESSAGE = "팔로워 대상을 찾을 수 없습니다.";
    public FollowerNotFoundException() {
        super(MESSAGE);
    }

}
