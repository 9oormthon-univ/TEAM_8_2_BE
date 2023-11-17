package com.example.clouddog.domain.comment.api.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentReqDto {
    private Long memberId;

    private Long previousCommentId;

    private String cmContent;


    public CommentReqDto(Long memberId,
                         String cmContent,
                         Long preCmId){
        this.memberId=memberId;
        this.cmContent=cmContent;
        this.previousCommentId=preCmId;
    }
}