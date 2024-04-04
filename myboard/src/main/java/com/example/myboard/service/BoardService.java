package com.example.myboard.service;

import com.example.myboard.model.DeleteStatus;
import com.example.myboard.model.dto.BoardDto;
import com.example.myboard.model.entity.Board;
import com.example.myboard.repository.BoardRepository;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;

    public Board saveBoard(Board board){
        return repository.save(board);
    }

    @Transactional
    public Optional<Board> deleteBoard(Long BoardNo){
        repository.updateStatusByBoardNo(BoardNo, DeleteStatus.DELETE);
        return repository.findById(BoardNo);
    }

    public List<BoardDto> findAllBoard(){
        return repository.findAll().stream()
                .map(BoardDto::ToBoardDtoWithoutContent)
                .sorted(Comparator.reverseOrder())
                .toList();
    }
}
