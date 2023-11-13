package com.example.clouddog.domain.board;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController//여기에 있으니까 아래 @RequestBody 다 생략해도 되나
@RequestMapping("/api")
public class BoardController {
    private BoardRepository boardRepository = new BoardRepository();

    /**
     * Board
     * 목록 조회: GET '/board/{bdTag}'
     * 글 등록: POST '/board
     * => body로 : {bdName}/{bdTime}/{bdPlace}/{bdTag}/{bdText}'
     * ->응답으로 bdId
     * 글 수정: PATCH '/board/{bdId}
     * => body로 : /수정사항'
     * 글 삭제: DELETE '/board/{bdId}'
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/boards/{bdTag}")
    public List<Board> board(@PathVariable Integer bdTag){
        if (bdTag==0)
            return boardRepository.findAll();
        else
            return boardRepository.findAllByTag(bdTag);
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/board")
    public Long addBoard(@RequestBody Board bdData){
        boardRepository.save(bdData);
        return bdData.getBdId();
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/board/{bdId}")
    public Board findBoard(@PathVariable Long bdId){
        return boardRepository.findById(bdId);
    }
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/board/{bdId}")
    public void updateBoard(@PathVariable Long bdId, @RequestBody BoardDto bdData){
        boardRepository.update(bdId, bdData);
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/board/{bdId}")
    public void deleteBoard(@PathVariable Long bdId){
        boardRepository.delete(bdId);
    }

}
