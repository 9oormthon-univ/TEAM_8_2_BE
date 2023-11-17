package com.example.clouddog.domain.comment.api.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResDto {

    private Long cmId;

    private String cmContent;

    private LocalDate cmTime;

    private int cmLikes;

    public CommentResDto(Long cmId, String cmContent, int cmLikes, LocalDate cmTime){
        this.cmId=cmId;
        this.cmContent=cmContent;
        this.cmLikes=cmLikes;
        this.cmTime=cmTime;
    }
}