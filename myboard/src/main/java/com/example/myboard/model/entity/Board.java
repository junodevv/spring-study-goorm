package com.example.myboard.model.entity;

import com.example.myboard.model.DeleteStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Board")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_no", nullable = false, unique = true)
    private Long boardNo;
    @Column
    private String title;
    @Column
    private String content;
    @Enumerated(EnumType.STRING) // Enum 에 정의한 이름의 문자열 자체가 사용된다.
    private DeleteStatus deleteStatus;
}
