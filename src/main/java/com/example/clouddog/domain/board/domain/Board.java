package com.example.clouddog.domain.board.domain;

import com.example.clouddog.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDate;

@Entity
@Getter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String boardTitle;

    @Temporal(TemporalType.DATE)
    private LocalDate boardTime;

    private String boardPlace;

    private Integer boardTag;

    @Lob
    private String boardContent;

    private String boardImageUrl;

    public Board() {
    }

    public Board(String boardTitle, String boardPlace,
                 Integer boardTag, String boardContent) {
        //this.member=member;
        this.boardTitle=boardTitle;
        this.boardPlace=boardPlace;
        this.boardTag=boardTag;
        this.boardContent=boardContent;
        this.boardTime=LocalDate.now();

        //member안에 list에 board추가.. 여기서 해야하나
    }
    public void update(String boardTitle, String boardPlace,
                 Integer boardTag, String boardContent) {
        //this.member=member;
        this.boardTitle=boardTitle;
        this.boardPlace=boardPlace;
        this.boardTag=boardTag;
        this.boardContent=boardContent;
        this.boardTime=LocalDate.now();

        //member안에 list에 board추가.. 여기서 해야하나
    }
}
