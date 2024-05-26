package com.example.sns.core.user.exception;

import com.example.sns.common.exception.ResourceNotFoundException;

public class NicknameHistoryNotFoundException extends ResourceNotFoundException {
    private static final String MESSAGE = "닉네임 이력을 찾을 수 없습니다.";

    public NicknameHistoryNotFoundException() {
        super(MESSAGE);
    }
}
