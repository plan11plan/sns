package com.example.sns.core.post.exception;

import com.example.sns.common.exception.ResourceNotFoundException;

public class CommentNotFoundException extends ResourceNotFoundException {
    private final static  String MESSAGE = "댓글을 찾을 수 없습니다.";
    private final static String ID_IS ="ID : ";
    public CommentNotFoundException() {
        super(MESSAGE);
    }
    public CommentNotFoundException(Long id) {
        super(ID_IS+ id + MESSAGE);
    }
}
