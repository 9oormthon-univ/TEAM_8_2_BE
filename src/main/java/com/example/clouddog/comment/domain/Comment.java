package com.example.clouddog.comment.domain;

import com.example.clouddog.board.domain.Board;
import com.example.clouddog.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    private Long previousCmId;

    private String commentContent;

    private int commentLikes;

    @Temporal(TemporalType.DATE)
    private LocalDate commentTime;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public Comment(Member member, Board board, String commentContent, Long previousCmId) {
        this.member = member;
        this.board = board;
        this.commentContent = commentContent;
        this.previousCmId = previousCmId;
        this.commentLikes = 0;
        this.commentTime = LocalDate.now();
    }

    public void update(String commentContent) {
        this.commentContent = commentContent;
        this.commentTime = LocalDate.now();
    }

    public void addLikes() {
        commentLikes += 1;
    }

    public void subLikes() {
        if (commentLikes > 0) {
            commentLikes -= 1;
        }
    }
}
