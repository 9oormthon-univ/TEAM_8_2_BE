package com.example.clouddog.domain.comment;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CommentRepository {
    //여기 맵인지 그냥 배열인지는 게시글마다 테이블이 있는가에 따라 달라짐
    //하지만 어차피 bd에 넣으면 아래 배열따위는 필요없음
    private static final ArrayList<Map<Long, Comment>> comments=new ArrayList<>();//글번호가 인덱스인 배열. 값은 댓글 들어간 map

    private static Long sequence =0L;

    public Comment save(Long boardId, Long preCmId, Comment cm){
        cm.setCmId(++sequence);
        cm.setCmTime(LocalDate.now());
        //대댓글이면 선댓글의 id 저장
        if (preCmId!=null)
            cm.setPreviousCmId(preCmId);
        //배열의 보드인덱스의 맵에 댓글 저장
        if (comments.size()<boardId || comments.isEmpty()){
            while (comments.size()<boardId){
                comments.add(new ConcurrentHashMap<>());
            }
            comments.add(new ConcurrentHashMap<>());
            comments.get(boardId.intValue()).put(cm.getCmId(), cm);
            return cm;
        }else {
            comments.add(new ConcurrentHashMap<>());
            comments.get(boardId.intValue()).put(cm.getCmId(), cm);
            return cm;
        }
    }
    public Comment findById(Long bdId, Long cmId) {
        return comments.get(bdId.intValue()).get(cmId);
    }

    public void addCmLikes(Long bdId, Long cmId){
        Comment cm = findById(bdId, cmId);
        cm.setCmLikes(cm.getCmLikes()+1);
    }
    public void subCmLikes(Long bdId, Long cmId){
        Comment cm = findById(bdId, cmId);
        if (cm.getCmLikes()>0)
            cm.setCmLikes(cm.getCmLikes()-1);
    }
    public List<Comment> findAll(Long bdId){
        return new ArrayList<>(comments.get(bdId.intValue()).values());
    }
    public void update(Long bdId, Long cmId, CommentDto updateCm){
        Comment findCm = findById(bdId, cmId);
        findCm.setCmContent(updateCm.getCmContent());
        findCm.setCmTime(LocalDate.now());
    }
    public void delete(Long bdId, Long cmId){
        comments.get(bdId.intValue()).remove(cmId);
    }

}
