package com.example.clouddog.domain.comment;

import com.example.clouddog.domain.board.domain.Board;
import com.example.clouddog.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    private Long previousCmId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    private String commentContent;

    private Integer commentLikes;

    @Temporal(TemporalType.DATE)
    private LocalDate commentTime;

    public Comment() {
    }
}
//대댓에 또 대댓을 달고 싶다면...?! => 상위댓글을 가르킴으로 해결
