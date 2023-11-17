package com.example.clouddog.domain.message.api.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageReqDto {
    private String msgContent;

    public MessageReqDto(String msgContent) {
        this.msgContent=msgContent;
    }
}
