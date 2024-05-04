package com.example.sns.core.post.exception;

import com.example.sns.common.exception.RootException;

public class NoValueTitle extends RootException {
    private static final String MESSAGE = "제목을 입력해주세요";
    public NoValueTitle() {
        super(MESSAGE);
    }

    @Override
    public String getStatusCode() {
        return "400";
    }
}
