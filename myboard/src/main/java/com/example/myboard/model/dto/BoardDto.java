package com.example.myboard.model.dto;

import com.example.myboard.model.DeleteStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class BoardDto implements Comparable<BoardDto> {
    private Long boardNo;
    private String title;
    @JsonInclude(Include.NON_EMPTY)
    private String content;
    private DeleteStatus deleteStatus;

    @Override
    public int compareTo(BoardDto o) {
        return (int)(this.boardNo - o.boardNo);
    }
}
