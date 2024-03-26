package com.example.student.service;

import com.example.student.model.Student;
import com.example.student.repository.StudentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    public void save(String name, int grade){
        repository.save(name, grade);
    }

    public List<Student> getAll (){
        return repository.findAll();
    }

    public List<Student> getAllByGrade(int grade){
        return repository.findByGrade(grade);
    }
}
