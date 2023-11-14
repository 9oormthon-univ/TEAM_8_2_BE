package com.example.clouddog.domain.board;

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
    private String bdImageUrls;

    public BoardDto() {
    }
}
