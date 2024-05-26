package com.example.sns.core.chat.exception;

import com.example.sns.common.exception.ResourceNotFoundException;

public class MessageNotFoundException extends ResourceNotFoundException {
    private final static  String MESSAGE = "메세지를 찾을 수 없습니다.";
    private final static String ID_IS ="ID : ";
    public MessageNotFoundException() {
        super(MESSAGE);
    }
    public MessageNotFoundException(Long id) {
        super(ID_IS+ id + MESSAGE);
    }
}
