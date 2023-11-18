package com.example.clouddog.comment.api.dto.response;

import com.example.clouddog.comment.domain.Comment;
import java.time.LocalDate;
import java.util.List;
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

    private String cmContent;

    private LocalDate cmTime;

    private int cmLikes;
    private List<Long> likeCommentMembers;

    @Builder
    private CommentResDto(Long cmId, Long memberId, Long boardId, String cmContent, int cmLikes, LocalDate cmTime,
                          List<Long> likeCommentMembers) {
        this.cmId = cmId;
        this.memberId = memberId;
        this.boardId = boardId;
        this.cmContent = cmContent;
        this.cmLikes = cmLikes;
        this.cmTime = cmTime;
        this.likeCommentMembers = likeCommentMembers;
    }

    public static CommentResDto of(Comment comment, List<Long> likeCommentMembers) {
        return CommentResDto.builder()
                .cmId(comment.getCommentId())
                .memberId(comment.getMember().getMemberId())
                .boardId(comment.getBoard().getBoardId())
                .cmContent(comment.getCommentContent())
                .cmTime(comment.getCommentTime())
                .cmLikes(comment.getCommentLikes())
                .likeCommentMembers(likeCommentMembers)
                .build();
    }
}