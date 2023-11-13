package com.example.clouddog.domain.comment;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class Comment {
    private Long bdId;
    private Long cmId;
    //private Map<Long, Long> cmId = new HashMap<>();
    private String memberId;
    private String cmContent;
    private String rcmContent;
    private LocalDate cmTime;

    public Comment() {
    }

//    public void setCmId(Long bdId, Long cmNum){
//        cmId.put(bdId, cmNum);
//    }
}
//대댓에 또 대댓을 달고 싶다면...?!
