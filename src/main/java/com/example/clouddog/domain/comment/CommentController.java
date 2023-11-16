package com.example.clouddog.domain.comment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class CommentController {

    private CommentRepository commentRepository = new CommentRepository();
    /**
     * 댓글 목록 조회
     * 댓글 작성
     * 댓글 수정
     * 댓글 삭제
     * 댓글 좋아요 생성
     * 댓글 좋아요 삭제
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/comments/{bdId}")
    public List<Comment> comments(@PathVariable Long bdId){
        return commentRepository.findAll(bdId);
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/comments/{bdId}/{preCmId}")
    public Long addComment(@PathVariable Long bdId, @PathVariable Long preCmId, @RequestBody Comment cmData){
        Comment cm = commentRepository.save(bdId, preCmId, cmData);
        return cm.getCommentId();
    }
    //불러오기가 필요할까........
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/comments/{bdId}/{cmId}")
//    public List<Comment> comments(@PathVariable Long bdId, @PathVariable Long cmId){
//        return commentRepository.findAll(bdId);
//    }
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/comments/{bdId}/{cmId}")
    public void updateComment(@PathVariable Long bdId, @PathVariable Long cmId, @RequestBody CommentDto cmData){
        commentRepository.update(bdId, cmId, cmData);
    }
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/comments/addLikes/{bdId}/{cmId}")
    public void addCmLikes(@PathVariable Long bdId, @PathVariable Long cmId){
        commentRepository.addCmLikes(bdId, cmId);
    }
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/comments/subLikes/{bdId}/{cmId}")
    public void subCmLikes(@PathVariable Long bdId, @PathVariable Long cmId){
        commentRepository.subCmLikes(bdId, cmId);
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/comments/{bdId}/{cmId}")
    public void deleteComment(@PathVariable Long bdId, @PathVariable Long cmId){
        commentRepository.delete(bdId, cmId);
    }

}
