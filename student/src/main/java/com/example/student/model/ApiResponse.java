package com.example.student.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter // 응답으로 보내줄수 있는 타입이 되도록 직렬화 역직렬화?를 해야해서 Getter는 꼭 붙여야 한대
@AllArgsConstructor
public class ApiResponse<T> {

    private Status status;
    private Metadata metadata;
    private List<T> results;
    private Object data;

    // 정상 응답 생성자
    public ApiResponse(List<T> results) {
        this.status = new Status(ErrorCode.OK.getCode(), ErrorCode.OK.getMessage());
        this.metadata = new Metadata(results.size());
        this.results = results;
    }

    // 에러 응답 생성자
    public ApiResponse(int code, String message, Object data) {
        this.status = new Status(code, message);
        this.data = data;
    }

    public ApiResponse<T> makeResponse(T result){
        return null;
    }

    public ApiResponse<T> makeResponse(List<T> results){

        return null;
    }

    @Getter
    @AllArgsConstructor
    private static class Status{
        private int code;
        private String message;
    }
    @Getter
    @AllArgsConstructor
    private static class Metadata{
        private int resultCount;
    }
}
