package com.example.clouddog.domain.board.api.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDto {
    private Long memberId;
    private Long bdId;
    private String bdTitle;
    private int bdTag;
    private String bdImageUrl;

    public BoardDto(Long bdId,
                    Long memberId,
                    String boardTitle,
                    int boardTag) {
        this.memberId=memberId;
        this.bdId=bdId;
        this.bdTitle=boardTitle;
        this.bdTag=boardTag;
        //나중에 이미지 추가
    }
}
