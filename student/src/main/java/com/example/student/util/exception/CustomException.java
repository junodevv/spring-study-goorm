package com.example.student.util.exception;

import com.example.student.model.ErrorCode;
import com.example.student.model.InputRestriction;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;
    private String message;
    private Data data;

    public CustomException(ErrorCode errorCode, String message, InputRestriction inputRestriction){
        this.errorCode = errorCode;
        this.message = message;
        this.data = new Data(inputRestriction);
    }

    @Getter
    @AllArgsConstructor
    private static class Data{
        private InputRestriction inputRestriction;
    }
}
