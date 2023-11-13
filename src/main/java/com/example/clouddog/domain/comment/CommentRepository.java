package com.example.clouddog.domain.comment;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CommentRepository {
    //private static final Map<Map<Long, Long>, Comment> comments = new ConcurrentHashMap<>();
    //private static final ArrayList<ArrayList<Comment>> comments=new ArrayList<>();//[글인덱스][댓글인덱스]
    private static final ArrayList<Map<Long, Comment>> comments=new ArrayList<>();//글번호가 인덱스인 배열. 값은 댓글 들어간 map
    private static Long sequence =0L;
    private static Long reSequence=0L;

    public Comment save(Long boardId, Comment cm){
        cm.setCmId(++sequence);
        if (comments.size()<boardId || comments.isEmpty()){//comments.isEmpty() || comments.get(boardId.intValue())==null){
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
    //대댓글 관련
    public List<ReComment> rcmFindAll(Long bdId, Long cmId){
        return findById(bdId, cmId).findAllRcm();
    }
    public Long rcmSave(Long bdId, Long cmId, ReComment rcmContent){
        Comment findCm = findById(bdId, cmId);
        rcmContent.setRcmId(++reSequence);
        findCm.setRcmContent(rcmContent.getRcmId(), rcmContent);
        return rcmContent.getRcmId();
    }
    public void rcmUpdate(Long bdId, Long cmId, Long rcmId, ReComment updateRcm){
        Comment findCm = findById(bdId, cmId);
        ReComment findRcm = findCm.getRcmContent(rcmId);
        findRcm.setRcmContent(updateRcm.getRcmContent());
        findRcm.setRcmTime(updateRcm.getRcmTime());
    }
    public void rcmDelete(Long bdId, Long cmId, Long rcmId){
        findById(bdId, cmId).deleteRcm(rcmId);
    }
}
