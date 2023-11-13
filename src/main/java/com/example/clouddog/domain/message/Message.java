package com.example.clouddog.domain.message;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class Message {
    private Long msgId;
    private String msgContent;
    private LocalDate msgTime;

    public Message() {
    }
}
