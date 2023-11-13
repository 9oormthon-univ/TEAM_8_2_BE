package com.example.clouddog.domain.comment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController//여기에 있으니까 아래 @RequestBody 다 생략해도 되나
@RequestMapping("/api")
public class CommentController {

    private CommentRepository commentRepository = new CommentRepository();
    /**
     * 댓글 목록 조회: GET '/comments/{bdId}'
     * 댓글 작성: POST '/comments/{bdId}
     * => body : /{memberId}/{cmText}'
     * 댓글 수정: PATCH '/comments/{cmId}
     * => body : /{cmText}'
     * 댓글 삭제: DELETE '/comments/{cmId}'
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/comments/{bdId}")
    public List<Comment> comments(@PathVariable Long bdId){
        return commentRepository.findAll(bdId);
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/comments/{bdId}")
    public Long addComment(@PathVariable Long bdId, @RequestBody Comment cmData){
        Comment cm = commentRepository.save(bdId, cmData);
        return cm.getCmId();
    }
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/comments/{bdId}/{cmId}")
    public void updateComment(@PathVariable Long bdId, @PathVariable Long cmId, @RequestBody CommentDto cmData){
        commentRepository.update(bdId, cmId, cmData);
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/comments/{bdId}/{cmId}")
    public void deleteComment(@PathVariable Long bdId, @PathVariable Long cmId){
        commentRepository.delete(bdId, cmId);
    }

    /**
     * 답글 모두 찾기
     * 답글 작성: POST '/comments/{cmId}
     * =>bodu : /{cmText}'
     * 답글 수정: PATCH '/comments/{rcmId}
     * =>body : /{cmText}'
     * 답글 삭제: DELETE '/comments/{rcmId}'
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/recomments/{bdId}/{cmId}")
    public List<ReComment> recomments(@PathVariable Long bdId, @PathVariable Long cmId){
        return commentRepository.rcmFindAll(bdId, cmId);
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/recomments/{bdId}/{cmId}")
    public Long addRecomment(@PathVariable Long bdId, @PathVariable Long cmId,
                             @RequestBody ReComment rcm){
        return commentRepository.rcmSave(bdId, cmId, rcm);
    }
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/recomments/{bdId}/{cmId}/{rcmId}")
    public void updateRecomment(@PathVariable Long bdId, @PathVariable Long cmId,
                                @PathVariable Long rcmId, @RequestBody ReComment updateRcm){
        commentRepository.rcmUpdate(bdId, cmId, rcmId, updateRcm);
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/recomments/{bdId}/{cmId}/{rcmId}")
    public void deleteRecomment(@PathVariable Long bdId, @PathVariable Long cmId,
                                @PathVariable Long rcmId){
        commentRepository.rcmDelete(bdId, cmId, rcmId);
    }

}
