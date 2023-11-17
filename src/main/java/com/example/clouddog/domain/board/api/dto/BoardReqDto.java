package com.example.clouddog.domain.board.api.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardReqDto {
    private String bdTitle;

    private String bdPlace;

    private int bdTag;

    private String bdContent;

    private String bdTime;

    private String bdImageUrl;

    public BoardReqDto(String boardTitle, String boardPlace,
                       int boardTag, String boardContent, String boardTime) {
        this.bdTitle = boardTitle;
        this.bdPlace = boardPlace;
        this.bdTag = boardTag;
        this.bdContent = boardContent;
        this.bdTime = boardTime;
        //나중에 이미지 추가
    }
}
