package com.example.student.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Student implements Comparable<Student>{
    private String name;
    private int grade;

    @Override
    public int compareTo(Student s) {
        return this.grade - s.getGrade(); // 메서드를 호출하는 객체가 비교대상보다 작은 경우 음수 반환 (오름차순)
    }
}
