package com.example.clouddog.domain.board.domain;

import com.example.clouddog.image.domain.Image;
import com.example.clouddog.member.domain.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image image;

    public Board(Member member, String boardTitle, String boardPlace,
                 int boardTag, String boardContent, String boardTime, Image image) {
        this.member = member;
        this.boardTitle = boardTitle;
        this.boardPlace = boardPlace;
        this.boardTag = boardTag;
        this.boardContent = boardContent;
        this.boardTime = boardTime;
        this.image = image;
    }

    public void update(String boardTitle, String boardPlace,
                       int boardTag, String boardContent, String boardTime) {
        this.boardTitle = boardTitle;
        this.boardPlace = boardPlace;
        this.boardTag = boardTag;
        this.boardContent = boardContent;
        this.boardTime = boardTime;

    }

}
