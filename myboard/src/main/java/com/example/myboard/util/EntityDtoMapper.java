package com.example.myboard.util;

import com.example.myboard.model.dto.BoardDto;
import com.example.myboard.model.entity.Board;

public class EntityDtoMapper {
    // 본문 미 포함된 응답
    public static BoardDto mapBoardToDtoNoContent(Board board){
        return BoardDto.builder()
                .boardNo(board.getBoardNo())
                .title(board.getTitle())
                .deleteStatus(board.getDeleteStatus())
                .build();
    }
    // 본문 포함 응답
    public static BoardDto mapBoardToDto(Board board){
        return BoardDto.builder()
                .boardNo(board.getBoardNo())
                .title(board.getTitle())
                .content(board.getContent())
                .deleteStatus(board.getDeleteStatus())
                .build();
    }

}
