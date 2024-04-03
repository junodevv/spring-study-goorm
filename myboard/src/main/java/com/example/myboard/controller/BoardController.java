package com.example.myboard.controller;

import com.example.myboard.model.entity.Board;
import com.example.myboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService service;

    @PostMapping("/board")
    public ResponseEntity saveBoard(@ModelAttribute Board board){
        log.info("board = {}", board);
        return ResponseEntity.ok(service.saveBoard(board));
    }
}
