package com.example.student.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    OK(2000, "OK", HttpStatus.OK),
    BAD_REQUEST(5000, "BAD_REQUEST", HttpStatus.OK);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;
}
