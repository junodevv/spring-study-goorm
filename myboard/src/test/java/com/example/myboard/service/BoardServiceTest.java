package com.example.myboard.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.myboard.model.DeleteStatus;
import com.example.myboard.model.entity.Board;
import com.example.myboard.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired BoardService service;
    @Autowired BoardRepository repository;

    @Test
    @DisplayName("게시글_저장_테스트")
    void 게시글_저장_테스트(){
        Board board = new Board();
        board.setTitle("Test title");
        board.setContent("Test Content");
        board.setDeleteStatus(DeleteStatus.ACTIVE);

        ResponseEntity responseEntity = service.saveBoard(board);

        System.out.println("responseEntity.getBody().toString() = " + responseEntity.getBody().toString());

        Assertions.assertThat(responseEntity.getBody()).isEqualTo(board);
    }
}