package com.example.clouddog.domain.comment.domain;

import com.example.clouddog.domain.board.domain.Board;
import com.example.clouddog.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
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

    public Comment(String commentContent, Integer commentLikes, Long previousCmId){
        this.commentContent=commentContent;
        this.previousCmId=previousCmId;
        if (commentLikes==null)
            this.commentLikes=0;
        else this.commentLikes=commentLikes;
        this.commentTime=LocalDate.now();
    }
    public void update(Long previousCmId, String commentContent, Integer commentLikes){
        this.previousCmId=previousCmId;
        this.commentContent=commentContent;
        this.commentLikes=commentLikes;
        this.commentTime=LocalDate.now();
    }

    public void setPreviousCmId(Long previousCmId) {
        this.previousCmId = previousCmId;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void addLikes(){
        if (commentLikes!=null){
            commentLikes+=1;
        }
    }
    public void subLikes(){
        if (commentLikes!=null &&commentLikes>0){
            commentLikes-=1;
        }
    }
}
