package com.example.clouddog.domain.comment.api;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CommentDto {

    private Long previousCommentId;

    private String cmContent;

    private LocalDate cmTime;

    private Integer cmLikes;

    public CommentDto() {
    }
    public CommentDto(String cmContent, Integer cmLikes, LocalDate cmTime, Long preCmId){
        this.cmContent=cmContent;
        this.cmLikes=cmLikes;
        this.cmTime=cmTime;
        this.previousCommentId=preCmId;
    }
}
//대댓에 또 대댓을 달고 싶다면...?!
