package com.example.myboard.service;

import com.example.myboard.model.DeleteStatus;
import com.example.myboard.model.dto.BoardDto;
import com.example.myboard.model.entity.Board;
import com.example.myboard.repository.BoardRepository;
import com.example.myboard.util.EntityDtoMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;
    // 게시글 등록
    public BoardDto saveBoard(Board board){
        return EntityDtoMapper.mapBoardToDto(repository.save(board));
    }
    // 게시글 삭제(soft)
    @Transactional
    public BoardDto deleteBoard(Long BoardNo){
        repository.updateStatusByBoardNo(BoardNo, DeleteStatus.DELETE);
        return EntityDtoMapper.mapBoardToDto(repository.findById(BoardNo).get());
    }
    // 게시글 목록 조회
    public List<BoardDto> findAllBoard(int pageNum, int pageSize){
        Sort sort = Sort.by(Direction.DESC, "boardNo");
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);

        return repository.findAll(pageable).stream()
                .map(EntityDtoMapper::mapBoardToDtoNoContent)
                .toList();
    }
    // 게시글 수정
    @Transactional
    public BoardDto updateBoard(Board board){
        if(repository.findById(board.getBoardNo()).get().getDeleteStatus() == DeleteStatus.DELETE){
            throw new IllegalArgumentException("이미 삭제된 게시물입니다.");
        }
        repository.updateBoardByBoardNo(board.getBoardNo(), board.getTitle(), board.getContent());
        Board updateResult = repository.findById(board.getBoardNo()).get();
        return EntityDtoMapper.mapBoardToDto(updateResult);
    }
}
