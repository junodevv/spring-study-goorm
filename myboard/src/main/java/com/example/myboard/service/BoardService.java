package com.example.myboard.service;

import com.example.myboard.model.entity.Board;
import com.example.myboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;

    public ResponseEntity saveBoard(Board board){
        return ResponseEntity.ok(repository.save(board));
    }

}
