package com.example.myboard.repository;

import com.example.myboard.model.DeleteStatus;
import com.example.myboard.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Modifying
    @Query("update Board b SET b.deleteStatus = :deleteStatus WHERE b.boardNo= :boardNo")
    void updateStatusByBoardNo(Long boardNo, DeleteStatus deleteStatus);
}
