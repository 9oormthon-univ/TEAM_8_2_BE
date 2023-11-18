package com.example.clouddog.comment.api.dto;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResDto {

    private Long cmId;
    private Long memberId;
    private Long boardId;

    private String cmContent;

    private LocalDate cmTime;

    private int cmLikes;

    public CommentResDto(Long cmId, Long memberId, Long boardId, String cmContent, int cmLikes, LocalDate cmTime) {
        this.cmId = cmId;
        this.memberId = memberId;
        this.boardId = boardId;
        this.cmContent = cmContent;
        this.cmLikes = cmLikes;
        this.cmTime = cmTime;
    }
}