package com.example.clouddog.domain.board;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;

@Getter @Setter
public class Board {
    private Long bdId;
    private String bdTitle;
    private LocalDate bdTime;
    private String bdPlace;
    private Integer bdTag;
    private String bdContent;
    private ArrayList<String> bdImageUrls;

    public Board() {
    }
}
