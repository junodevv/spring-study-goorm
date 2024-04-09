package com.example.myboard.model.entity;

import com.example.myboard.model.DeleteStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "Comment")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_no", nullable = false, unique = true)
    private Long commentNo;
    @ManyToOne // <- 주인 Entity
    @JoinColumn(name = "board_no", referencedColumnName = "board_no")
    private Board board;
    @Column(length = 500)
    private String content;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ACTIVE'")
    private DeleteStatus deleteStatus;

    public Comment() {
        this.deleteStatus = DeleteStatus.ACTIVE;
    }

}
