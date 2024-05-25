package com.example.sns.core.follow.exception;

import com.example.sns.core.common.exception.SnsException;

public class FollowNotFoundException extends SnsException {
    private final static String MESSAGE ="FollowNotFountException입니다.";
    public FollowNotFoundException() {
        super(MESSAGE);
    }


    @Override
    public String getStatusCode() {
        return null;
    }
}
