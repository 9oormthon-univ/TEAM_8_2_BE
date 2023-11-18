package com.example.clouddog.comment.api;

import com.example.clouddog.comment.api.dto.CommentReqDto;
import com.example.clouddog.comment.api.dto.CommentResDto;
import com.example.clouddog.comment.application.CommentService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    //댓글 불러오기 -> 보드에서 불러오기로=>실패
    @GetMapping("/{memberId}/comments/{bdId}")
    public ResponseEntity<List<CommentResDto>> comments(@PathVariable Long memberId, @PathVariable Long bdId) {
        return new ResponseEntity<>(commentService.findAll(bdId), HttpStatus.OK);
    }

    //댓글 작성
    @PostMapping("/{memberId}/comments/{bdId}")
    public ResponseEntity<String> addComment(@PathVariable Long memberId, @PathVariable Long bdId,
                                             @RequestBody CommentReqDto commentDto) {
        commentService.commentSave(bdId, commentDto);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    //댓글 수정
    @PatchMapping("/{memberId}/comments/{cmId}")
    public ResponseEntity<String> updateComment(@PathVariable Long memberId, @PathVariable Long cmId,
                                                @RequestBody CommentReqDto commentDto) {
        commentService.commentUpdate(cmId, commentDto);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    //좋아요 추가
    @PatchMapping("/comments/addLikes/{cmId}")
    public ResponseEntity<String> addCmLikes(@PathVariable Long cmId) {
        commentService.addCmLikes(cmId);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    //좋아요 취소
    @PatchMapping("/comments/subLikes/{cmId}")
    public ResponseEntity<String> subCmLikes(@PathVariable Long cmId) {
        commentService.subCmLikes(cmId);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    //댓글 삭제
    @DeleteMapping("/comments/{cmId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long cmId) {
        commentService.commentDelete(cmId);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

}
