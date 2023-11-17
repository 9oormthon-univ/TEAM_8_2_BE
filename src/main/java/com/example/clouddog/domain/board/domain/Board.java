package com.example.clouddog.domain.board.domain;

import com.example.clouddog.domain.comment.domain.Comment;
import com.example.clouddog.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    //실패
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "boardId")
//    private final List<Comment> comments=new ArrayList<>();

    private String boardTitle;

    private String boardTime;

    private String boardPlace;

    private int boardTag;

    @Lob
    private String boardContent;

    private String boardImageUrl;

    public Board(Member member, String boardTitle, String boardPlace,
                 int boardTag, String boardContent, String boardTime) {
        this.member=member;
        this.boardTitle=boardTitle;
        this.boardPlace=boardPlace;
        this.boardTag=boardTag;
        this.boardContent=boardContent;
        this.boardTime=boardTime;

    }
    public void update(String boardTitle, String boardPlace,
                 int boardTag, String boardContent, String boardTime) {
        this.boardTitle=boardTitle;
        this.boardPlace=boardPlace;
        this.boardTag=boardTag;
        this.boardContent=boardContent;
        this.boardTime=boardTime;

    }
}
