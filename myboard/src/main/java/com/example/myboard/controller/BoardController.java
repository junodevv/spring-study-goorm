package com.example.myboard.controller;

import com.example.myboard.model.entity.Board;
import com.example.myboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    // 게시글 등록
    @PostMapping("/board")
    public ResponseEntity saveBoard(@ModelAttribute Board board) {
        log.info("board = {}", board);
        return ResponseEntity.ok(service.saveBoard(board));
    }

    // 게시글 삭제(soft)
    @GetMapping("/board/delete/{boardNo}")
    public ResponseEntity deleteBoard(@PathVariable("boardNo") Long boardNo) {
        return ResponseEntity.ok(service.deleteBoard(boardNo));
    }

    // 게시글 목록 조회
    @GetMapping("/board")
    public ResponseEntity findAllBoard(
            @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize
    ) {
        return ResponseEntity.ok(service.findAllBoard(pageNum, pageSize));
    }

    // 게시글 수정
    @PostMapping("/board/update")
    public ResponseEntity updateBoard(@ModelAttribute Board board) {
        return ResponseEntity.ok(service.updateBoard(board));
    }
}
