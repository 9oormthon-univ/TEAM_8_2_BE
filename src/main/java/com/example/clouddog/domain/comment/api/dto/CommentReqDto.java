package com.example.clouddog.domain.comment.api.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentReqDto {

    private Long previousCommentId;

    private String cmContent;


    public CommentReqDto(String cmContent,
                         Long preCmId){
        this.cmContent=cmContent;
        this.previousCommentId=preCmId;
    }
}