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
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "Board")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_no", nullable = false, unique = true)
    private Long boardNo;
    @Column
    private String title;
    @Column(length = 1000)
    private String content;
    @Enumerated(EnumType.STRING)// Enum 에 정의한 이름의 문자열 자체가 사용된다.
    @ColumnDefault("'ACTIVE'")
    private DeleteStatus deleteStatus;

    /**
     * 스프링이 생성자로 사용하는거 1순위가 파라메터가 없는 기본생성자인데 그 기본생성자에 기본값을 정해놓을 수 있다.
     * 이제 이 기본생성자로 객체를 생성하고 거기에 setter를 이용해서 클라이언트에서 받아온 파라메터들을 세팅한다.
     */
    public Board() {
        this.deleteStatus = DeleteStatus.ACTIVE;
    }
}
