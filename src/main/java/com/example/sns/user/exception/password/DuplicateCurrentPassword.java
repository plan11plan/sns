package com.example.sns.user.exception.password;

import com.example.sns.common.exception.RootException;

public class DuplicateCurrentPassword extends RootException {
    private static final String MESSAGE = "같은 비밀번호로 변경할 수 없습니다.";

    public DuplicateCurrentPassword() {
        super(MESSAGE);
    }

    @Override
    public String getStatusCode() {
        return "409"; // Conflict 상태 코드 반환
    }
}

