package com.example.clouddog.domain.board.exception;
public class NotFoundImageException extends RuntimeException{
    public NotFoundImageException(final String message) {
        super(message);
    }

    public NotFoundImageException() {
        this("이미지를 찾을 수 없습니다.");
    }
}