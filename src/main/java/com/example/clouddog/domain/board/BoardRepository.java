package com.example.clouddog.domain.board;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BoardRepository {
    private static final Map<Long, Board> boards = new ConcurrentHashMap<>();
    private static long sequence =0L;

    public Board save(Board bd){
        bd.setBdId(++sequence);
        bd.setBdTime(LocalDate.now());
        boards.put(bd.getBdId(), bd);
        return bd;
    }
    public Board findById(Long id) {return boards.get(id);}

    public List<Board> findAll(){
        return new ArrayList<>(boards.values());
    }
    public List<Board> findAllByTag(Integer bdTag){
        List<Board> returnList = new ArrayList<>();
        for (Board bd: boards.values()) {
            if (bd.getBdTag().equals(bdTag)){
                returnList.add(bd);
            }
        }
        return returnList;
    }
    public void update(Long bdId, BoardDto updateBd){
        Board findBd = findById(bdId);
        findBd.setBdTitle(updateBd.getBdTitle());
        findBd.setBdPlace(updateBd.getBdPlace());
        findBd.setBdTag(updateBd.getBdTag());
        findBd.setBdContent(updateBd.getBdContent());
        findBd.setBdTime(LocalDate.now());
        findBd.setBdImageUrls(updateBd.getBdImageUrls());
    }
    public void delete(Long bdId){
        boards.remove(bdId);
    }
}
