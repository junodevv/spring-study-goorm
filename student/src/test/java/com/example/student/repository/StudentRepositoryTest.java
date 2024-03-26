package com.example.student.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.student.model.Student;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentRepositoryTest {

    private StudentRepository repository;

    @BeforeEach
    void before(){
        repository = new StudentRepository();
    }

    @Test
    void save() {
        repository.save("ha",2);

        System.out.println("repository.findAll() = " + repository.findAll());

        assertThat(repository.findAll().size()).isEqualTo(3);
    }

    @Test
    void findAll() {
        List<Student> students = repository.findAll();

        System.out.println("students = " + students);

        assertThat(students.size()).isEqualTo(2);
    }

    @Test
    void findByGrade() {
        List<Student> studentsByGrade = repository.findByGrade(1);

        System.out.println("studentsByGrade = " + studentsByGrade);

        assertThat(studentsByGrade.size()).isEqualTo(1);

    }
}