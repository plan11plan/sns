package com.example.sns.user.exception;

import com.example.sns.common.exception.RootException;

public class DuplicateNickname extends RootException {
    private static final String MESSAGE = "다른 닉네임을 사용해주세요.";

    public DuplicateNickname() {
        super(MESSAGE);
    }

    @Override
    public String getStatusCode() {
        return "409"; // Conflict 상태 코드 반환
    }
}

