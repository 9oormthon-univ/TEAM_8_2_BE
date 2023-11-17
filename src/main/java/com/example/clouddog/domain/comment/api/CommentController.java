package com.example.clouddog.domain.comment.api;

import com.example.clouddog.domain.comment.api.dto.CommentReqDto;
import com.example.clouddog.domain.comment.api.dto.CommentResDto;
import com.example.clouddog.domain.comment.application.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class CommentController {

    private final CommentService commentService;
    public CommentController(CommentService commentService){
        this.commentService=commentService;
    }



    //댓글 불러오기 -> 보드에서 불러오기로=>실패
    @GetMapping("/comments/{bdId}")
    public ResponseEntity<List<CommentResDto>> comments(@PathVariable Long bdId){
        return new ResponseEntity<>(commentService.findAll(bdId), HttpStatus.OK);
    }

    //댓글 작성
    @PostMapping("/comments/{bdId}")
    public ResponseEntity<String> addComment(@PathVariable Long bdId, @RequestBody CommentReqDto commentDto){
        commentService.commentSave(bdId, commentDto);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    //댓글 수정
    @PatchMapping("/comments/{cmId}")
    public ResponseEntity<String> updateComment(@PathVariable Long cmId, @RequestBody CommentReqDto commentDto){
        commentService.commentUpdate(cmId, commentDto);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    //좋아요 추가
    @PatchMapping("/comments/addLikes/{cmId}")
    public ResponseEntity<String> addCmLikes(@PathVariable Long cmId){
        commentService.addCmLikes(cmId);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    //좋아요 취소
    @PatchMapping("/comments/subLikes/{cmId}")
    public ResponseEntity<String> subCmLikes(@PathVariable Long cmId){
        commentService.subCmLikes(cmId);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    //댓글 삭제
    @DeleteMapping("/comments/{cmId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long cmId){
        commentService.commentDelete(cmId);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

}
