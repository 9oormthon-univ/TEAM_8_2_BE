package com.example.clouddog.domain.comment.api;

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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/comments/{bdId}")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long bdId){
        return new ResponseEntity<>(commentService.findAll(bdId), HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/comments/{bdId}")
    public ResponseEntity<Long> addComment(@PathVariable Long bdId, @RequestBody CommentDto commentDto){
        Long cmId = commentService.commentSave(bdId, commentDto);
        return new ResponseEntity<>(cmId, HttpStatus.OK);
    }
    //불러오기가 필요할까........
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/comments/{bdId}/{cmId}")
//    public List<Comment> comments(@PathVariable Long bdId, @PathVariable Long cmId){
//        return commentRepository.findAll(bdId);
//    }
    //@PatchMapping("/comments/{bdId}/{cmId}")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/comments/{cmId}")
    public ResponseEntity<String> updateComment(@PathVariable Long cmId, @RequestBody CommentDto commentDto){
        commentService.commentUpdate(cmId, commentDto);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
    //@PatchMapping("/comments/addLikes/{bdId}/{cmId}")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/comments/addLikes/{cmId}")
    public ResponseEntity<String> addCmLikes(@PathVariable Long cmId){
        commentService.addCmLikes(cmId);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/comments/subLikes/{cmId}")
    public ResponseEntity<String> subCmLikes(@PathVariable Long cmId){
        commentService.subCmLikes(cmId);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/comments/{cmId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long cmId){
        commentService.commentDelete(cmId);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

}
