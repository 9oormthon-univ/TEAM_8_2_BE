package com.example.clouddog.comment.api.dto.response;

import com.example.clouddog.comment.domain.Comment;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResDto {

    private Long cmId;

    private Long memberId;

    private Long boardId;

    private Long previousCmId;

    private String cmContent;

    private LocalDate cmTime;

    private int cmLikes;

    @Builder
    private CommentResDto(Long cmId, Long memberId, Long boardId, Long previousCmId, String cmContent, int cmLikes, LocalDate cmTime) {
        this.cmId = cmId;
        this.memberId = memberId;
        this.boardId = boardId;
        this.previousCmId=previousCmId;
        this.cmContent = cmContent;
        this.cmLikes = cmLikes;
        this.cmTime = cmTime;
    }

    public static CommentResDto of(Comment comment) {
        return CommentResDto.builder()
                .cmId(comment.getCommentId())
                .memberId(comment.getMember().getMemberId())
                .previousCmId(comment.getPreviousCmId())
                .boardId(comment.getBoard().getBoardId())
                .cmContent(comment.getCommentContent())
                .cmTime(comment.getCommentTime())
                .cmLikes(comment.getCommentLikes())
                .build();
    }
}