package com.example.clouddog.domain.message.api.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageResDto {
    private Long msgId;

    private String msgContent;

    private String msgTime;

    public MessageResDto(Long msgId, String msgContent, String msgTime) {
        this.msgId=msgId;
        this.msgTime=msgTime;
        this.msgContent=msgContent;
    }
}
