package com.example.clouddog.domain.comment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
//@Entity
public class Comment {
    //@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "member_id")
    private Long cmId;
    private Long previousCmId;
    private String memberId;
    private String cmContent;
    private Integer cmLikes;
    private LocalDate cmTime;

    public Comment() {
    }
}
//대댓에 또 대댓을 달고 싶다면...?! => 상위댓글을 가르킴으로 해결
