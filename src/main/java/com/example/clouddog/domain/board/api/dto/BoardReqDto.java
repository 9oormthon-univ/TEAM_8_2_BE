package com.example.clouddog.domain.board.api.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardReqDto {

    private String memberId;

    private String bdTitle;

    private String bdPlace;

    private int bdTag;

    private String bdContent;

    private String bdTime;

    private String bdImageUrl;

    public BoardReqDto(String memberId, String boardTitle, String boardPlace,
                    int boardTag, String boardContent, String boardTime) {
        this.memberId=memberId;
        this.bdTitle=boardTitle;
        this.bdPlace=boardPlace;
        this.bdTag=boardTag;
        this.bdContent=boardContent;
        this.bdTime=boardTime;
        //나중에 이미지 추가
    }
}
