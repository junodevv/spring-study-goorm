package com.example.myboard.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.myboard.model.DeleteStatus;
import com.example.myboard.model.entity.Board;
import com.example.myboard.repository.BoardRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Commit;
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

        Board board1 = service.saveBoard(board);

        System.out.println("board1.toString() = " + board1.toString());

        Assertions.assertThat(board1).isEqualTo(board);
    }

    @Test
    @DisplayName("게시글_삭제(soft)_테스트")
    void 게시글_삭제_테스트(){
        Board board = new Board();
        board.setTitle("Test title");
        board.setContent("Test Content");

        Board board1 = service.saveBoard(board);
        System.out.println("board1.toString() = " + board1.toString());
        Optional deleteBoard = softDelete(board1.getBoardNo());
        System.out.println("deleteBoard.get() = " + deleteBoard.get());

//        Assertions.assertThat(deleteBoard.get().getDeleteStatus()).isEqualTo(DeleteStatus.DELETE);
    }
    @Commit
    private Optional<Board> softDelete(Long boardNo){
        return service.deleteBoard(boardNo);
    }
}