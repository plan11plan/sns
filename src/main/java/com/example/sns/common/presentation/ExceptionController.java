package com.example.sns.common.presentation;

import com.example.sns.common.exception.SnsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ResponseBody
    @ExceptionHandler(SnsException.class)
    public ResponseEntity<ErrorResponse> snsException(SnsException e){
        String statusCode = e.getStatusCode();
        ErrorResponse response = ErrorResponse.builder()
                .code(statusCode)
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();
        return ResponseEntity.status(Integer.parseInt(statusCode)).body(response);
    }
}
