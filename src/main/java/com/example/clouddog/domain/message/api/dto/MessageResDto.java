package com.example.clouddog.domain.message.api.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageResDto {
    private Long msgId;

    private String msgContent;

    private LocalDate msgTime;

    public MessageResDto(Long msgId, String msgContent, LocalDate msgTime) {
        this.msgId=msgId;
        this.msgTime=msgTime;
        this.msgContent=msgContent;
    }
}
