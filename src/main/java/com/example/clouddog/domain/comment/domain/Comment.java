package com.example.clouddog.domain.comment.domain;

import com.example.clouddog.domain.board.domain.Board;
import com.example.clouddog.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    private int commentLikes;

    @Temporal(TemporalType.DATE)
    private LocalDate commentTime;

    public Comment(String commentContent, Long previousCmId, Board board){
        this.board=board;
        this.commentContent=commentContent;
        this.previousCmId=previousCmId;
        this.commentLikes=0;
        this.commentTime=LocalDate.now();
    }
    public void update(String commentContent){
        this.commentContent=commentContent;
        this.commentTime=LocalDate.now();
    }

    public void addLikes(){
        commentLikes+=1;
    }
    public void subLikes(){
        if (commentLikes>0){
            commentLikes-=1;
        }
    }
}
