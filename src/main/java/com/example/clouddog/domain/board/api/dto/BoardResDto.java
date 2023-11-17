package com.example.clouddog.domain.board.api.dto;

import com.example.clouddog.domain.comment.domain.Comment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResDto {

    private Long bdId;

    private String bdTitle;

    private String bdTime;

    private String bdPlace;

    private int bdTag;

    private String bdContent;

    private String bdImageUrl;

    //private List<Comment> comments;

    public BoardResDto(Long bdId, String boardTitle, String boardPlace,
                    String boardTime, int boardTag, String boardContent){ //, List<Comment> comments) {
        this.bdId=bdId;
        this.bdTitle=boardTitle;
        this.bdPlace=boardPlace;
        this.bdTag=boardTag;
        this.bdContent=boardContent;
        this.bdTime=boardTime;
        //this.comments=comments;
        //나중에 이미지 추가
    }
}
