package com.example.sns.core.user.exception;


import com.example.sns.common.exception.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {
    private static final String MESSAGE = "사용자를 찾을 수 없습니다.";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
