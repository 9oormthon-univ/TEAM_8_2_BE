package com.example.clouddog.domain.board.api;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class BoardDto {
    private String bdTitle;
    private LocalDate bdTime;
    private String bdPlace;
    private Integer bdTag;
    private String bdContent;
    private String bdImageUrl;

    public BoardDto() {
    }
    public BoardDto(String boardTitle, String boardPlace,
                    Integer boardTag, String boardContent) {
        this.bdTitle=boardTitle;
        this.bdPlace=boardPlace;
        this.bdTag=boardTag;
        this.bdContent=boardContent;
        this.bdTime=LocalDate.now();
        //나중에 이미지 추가
    }
}
