package com.example.sns.core.post.exception;

import com.example.sns.common.exception.SnsException;
import lombok.Getter;

@Getter
public class InvalidRequest extends SnsException {
    private final static String MESSAGE = "유효하지 않은 요청";

    public InvalidRequest() {
        super(MESSAGE);
    }

    public InvalidRequest(String fieldName, String message){
        super(MESSAGE);
        addValidation(fieldName,message);
    }
    @Override
    public String getStatusCode() {
        return "400";
    }
}
