package com.example.clouddog.domain.board;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter @Setter
public class Board {
    private Long bdId;
    //외래키 표시 어떻게..?!
    private Long memberId;
    private String bdTitle;
    private LocalDate bdTime;
    private String bdPlace;
    private Integer bdTag;
    private String bdContent;
    private String bdImageUrls;

    public Board() {
    }
}
