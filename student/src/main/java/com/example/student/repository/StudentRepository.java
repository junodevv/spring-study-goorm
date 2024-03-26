package com.example.student.repository;

import com.example.student.model.Student;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private List<Student> students = Student.defaultStudent();

    public void save(String name, int grade){
        students.add(new Student(name, grade));
    }

    public List<Student> findAll(){
        return students.stream()
                .sorted(Comparator.naturalOrder())
                .toList();
    }

    public List<Student> findByGrade(int grade){
        return students.stream()
                .filter(i -> i.getGrade()==grade)
                .toList();
    }


}
