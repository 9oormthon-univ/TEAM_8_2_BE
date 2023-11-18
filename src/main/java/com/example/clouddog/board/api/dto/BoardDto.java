package com.example.clouddog.board.api.dto;

import com.example.clouddog.image.domain.Image;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDto {
    private Long memberId;
    private Long bdId;
    private String bdTitle;
    private int bdTag;
    private Image bdImage;

    public BoardDto(Long bdId,
                    Long memberId,
                    String boardTitle,
                    int boardTag,
                    Image image
                    ) {
        this.memberId = memberId;
        this.bdId = bdId;
        this.bdTitle = boardTitle;
        this.bdTag = boardTag;
        this.bdImage = image;
        //나중에 이미지 추가
    }
}
