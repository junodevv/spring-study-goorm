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

    public Student addStudent(String name, int grade){
        Student student = new Student(name, grade);
        repository.save(student);
        return student;
    }

    public List<Student> getAll (){
        return repository.findAll();
    }

    public List<Student> getAllByGrade(int grade){
        return repository.findByGrade(grade);
    }
}
