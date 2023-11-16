package com.example.clouddog.domain.comment;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CommentDto {

    private String commentContent;

    private LocalDate commentTime;

    public CommentDto() {
    }
}
//대댓에 또 대댓을 달고 싶다면...?!
