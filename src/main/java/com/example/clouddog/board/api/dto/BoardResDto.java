package com.example.clouddog.board.api.dto;

import com.example.clouddog.comment.api.dto.CommentResDto;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResDto {

    private Long bdId;

    private String bdTitle;

    private String bdTime;

    private String bdPlace;

    private int bdTag;

    private String bdContent;

    private String imageUrl;

    List<CommentResDto> comments = new ArrayList<>();

    public BoardResDto(Long bdId, String boardTitle, String boardPlace,
                       String boardTime, int boardTag, String boardContent, String imageUrl,
                       List<CommentResDto> comments) {
        this.bdId = bdId;
        this.bdTitle = boardTitle;
        this.bdPlace = boardPlace;
        this.bdTag = boardTag;
        this.bdContent = boardContent;
        this.bdTime = boardTime;
        this.imageUrl = imageUrl;
        this.comments = comments;
    }
}
