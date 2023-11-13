package com.example.clouddog.domain.comment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController//여기에 있으니까 아래 @RequestBody 다 생략해도 되나
@RequestMapping("/api")
public class CommentController {

    /**
     * 댓글 목록 조회: GET '/comments/{bdId}'
     * 댓글 작성: POST '/comments/{bdId}
     * => body : /{memberId}/{cmText}'
     * 댓글 수정: PATCH '/comments/{cmId}
     * => body : /{cmText}'
     * 댓글 삭제: DELETE '/comments/{cmId}'
     * 답글 작성: POST '/comments/{cmId}
     * =>bodu : /{cmText}'
     * 답글 수정: PATCH '/comments/{rcmId}
     * =>body : /{cmText}'
     * 답글 삭제: DELETE '/comments/{rcmId}'
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/comments")
    public List<Comment> comments(){
        //리스트 필요
        return null;
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/comments/{bdId}")
    public Long addComment(@PathVariable String bdId, @RequestBody Comment cmData){
        //댓글객체를 보드와 연결시켜주는게, 외래키로 연결이면 되는거겠지
        //댓글 객체 리스트 저장 로직 필요
        cmData.setBdId(Long.parseLong(bdId));
        return cmData.getCmId();
    }
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/comments/{bdId}/{cmId}")
    public void updateComment(@PathVariable String bdId, @PathVariable String cmId, @RequestBody Comment cmData){
        //수정 로직 필요
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/comments/{bdId}/{cmId}")
    public void deleteComment(@PathVariable String bdId, @PathVariable String cmId){
        //삭제 로직 필요
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/recomments/{cmId}")
    public void addRecomment(@PathVariable String cmId, @RequestBody Comment cmData){
        //기존 객체 찾아서_bdId도 필요하려나
        //대댓글 컨텐트만 저장하는 작업 필요
    }
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/recomments/{cmId}")
    public void updateRecomment(@PathVariable String cmId, @RequestBody Comment cmData){
        //댓글 객체 찾아서_bdId,cmId
        //대댓글 내용 수정 및 저장
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/recomments/{cmId}")
    public void deleteRecomment(@PathVariable String cmId){
        //삭제 작업 필요
    }
}
