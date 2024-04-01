package com.example.student.util.exception;

import com.example.student.model.InputRestriction;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private int code;
    private String message;
    private Data data;

    public CustomException(int code, String message, InputRestriction inputRestriction){
        this.code = code;
        this.message = message;
        this.data = new Data(inputRestriction);
    }

    @Getter
    @AllArgsConstructor
    private static class Data{
        private InputRestriction inputRestriction;
    }
}
