package com.example.student.util.exception.handler;

import com.example.student.model.ApiResponse;
import com.example.student.util.exception.CustomException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.example.student")
public class StudentExHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomException.class)
    public ApiResponse customExceptionHandler(CustomException e){
        log.error("[customException] e",e);
        return new ApiResponse(e.getCode(), e.getMessage(), e.getData());
    }
}
