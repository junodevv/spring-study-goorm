package com.example.student.controller;

import com.example.student.model.Student;
import com.example.student.service.StudentService;
import com.example.student.model.ApiResponse;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @PostMapping("/student")
    public ApiResponse addStudent (@RequestParam String name, @RequestParam("grade") int grade){
        log.info("name: {}, grade: {}", name, grade);

        return makeResponse(service.addStudent(name, grade));
    }

    @GetMapping("/student/all")
    public ApiResponse searchAllStudent(){
        return makeResponse(service.getAll());
    }

    @GetMapping("/student/{grade}")
    public ApiResponse searchByGrade(@RequestParam("grade") int grade){
        return makeResponse(service.getAllByGrade(grade));
    }

    private <T> ApiResponse<T> makeResponse(List<T> results) {
        return new ApiResponse<>(results);
    }

    private <T> ApiResponse<T> makeResponse(T result) {
        return new ApiResponse<>(Collections.singletonList(result)); // 하나짜리 리스트를 만드는 방법
    }
}
