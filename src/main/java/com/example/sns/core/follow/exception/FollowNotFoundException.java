package com.example.sns.core.follow.exception;

import com.example.sns.core.common.exception.RootException;

public class FollowNotFoundException extends RootException {
    private final static String MESSAGE ="FollowNotFountException입니다.";
    public FollowNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public String getStatusCode() {
        return null;
    }
}
