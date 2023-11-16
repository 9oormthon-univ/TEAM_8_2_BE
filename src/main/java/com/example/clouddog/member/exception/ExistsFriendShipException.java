package com.example.clouddog.member.exception;

public class ExistsFriendShipException extends RuntimeException {
    public ExistsFriendShipException(String message) {
        super(message);
    }

    public ExistsFriendShipException() {
        this("이미 존재하는 친구입니다.");
    }
}
