package com.example.clouddog.domain.comment;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class Comment {
    private Long cmId;
    private String memberId;
    private String cmContent;
    private Map<Long, ReComment> rcmContent=new ConcurrentHashMap<>();
    private LocalDate cmTime;

    public Comment() {
    }

    public void setRcmContent(Long rcmId, ReComment rcm){
        rcmContent.put(rcmId, rcm);
    }
    public ReComment getRcmContent(Long rcmId){
        return rcmContent.get(rcmId);
    }
    public List<ReComment> findAllRcm(){
        return new ArrayList<>(rcmContent.values());
    }
    public void deleteRcm(Long rcmId){
        rcmContent.remove(rcmId);
    }
}
//대댓에 또 대댓을 달고 싶다면...?!
