package com.example.student.controller;

import com.example.student.model.ErrorCode;
import com.example.student.model.InputRestriction;
import com.example.student.service.StudentService;
import com.example.student.model.ApiResponse;
import com.example.student.util.exception.CustomException;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    static final int maxGrade = 6;

    private final StudentService service;

    @PostMapping
    public ApiResponse addStudent (@RequestParam String name, @RequestParam("grade") int grade){
        log.info("name: {}, grade: {}", name, grade);
        if(5 < grade){
            throw new CustomException(ErrorCode.BAD_REQUEST,"grade 는 6이상을 입력 할 수 없습니다.", new InputRestriction(maxGrade));
        }
        return makeResponse(service.addStudent(name, grade));
    }

    @GetMapping("/all")
    public ApiResponse searchAllStudent(){
        return makeResponse(service.getAll());
    }

    @GetMapping("/{grade}")
    public ApiResponse searchByGrade(@PathVariable("grade") int grade){
        return makeResponse(service.getAllByGrade(grade));
    }

    private <T> ApiResponse<T> makeResponse(List<T> results) {

        return new ApiResponse(results);
    }

    private <T> ApiResponse<T> makeResponse(T result) {
        return new ApiResponse(Collections.singletonList(result)); // 하나짜리 리스트를 만드는 방법
    }
}
