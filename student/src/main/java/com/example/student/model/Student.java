package com.example.student.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Student implements Comparable<Student>{
    private String name;
    private int grade;

    @Override
    public int compareTo(Student s) {
        return this.grade - s.getGrade(); // 메서드를 호출하는 객체가 비교대상보다 작은 경우 음수 반환 (오름차순)
    }

    public static List<Student> defaultStudent(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("kim", 1));
        students.add(new Student("lee", 3));
        return students;
    }

}
