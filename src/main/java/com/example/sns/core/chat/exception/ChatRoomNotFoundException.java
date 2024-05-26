package com.example.sns.core.chat.exception;

import com.example.sns.common.exception.ResourceNotFoundException;

public class ChatRoomNotFoundException extends ResourceNotFoundException {
    private final static  String MESSAGE = "채팅방을 찾을 수 없습니다.";
    private final static String ID_IS ="ID : ";
    public ChatRoomNotFoundException() {
        super(MESSAGE);
    }
    public ChatRoomNotFoundException(Long id) {
        super(ID_IS+ id + MESSAGE);
    }
}
