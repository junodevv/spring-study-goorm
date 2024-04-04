package com.example.myboard.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.myboard.model.DeleteStatus;
import com.example.myboard.model.dto.BoardDto;
import com.example.myboard.model.entity.Board;
import com.example.myboard.repository.BoardRepository;
import java.util.List;
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

        assertThat(board1).isEqualTo(board);
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

    @Test
    @DisplayName("게시글 조회 테스트")
    void 게시글_조회_테스트(){
        Board board1 = new Board();
        board1.setTitle("Test title1");
        board1.setContent("Test Content1");
        service.saveBoard(board1);
        Board board2 = new Board();
        board2.setTitle("Test title22");
        board2.setContent("Test Content22");
        service.saveBoard(board2);
        int pageNum = 0;
        int pageSize = 0;
        
        List<BoardDto> resultList = service.findAllBoard(pageNum, pageSize);
        System.out.println("resultList.toString() = " + resultList.toString());
        assertThat(resultList.size()).isEqualTo(2);
    }
}