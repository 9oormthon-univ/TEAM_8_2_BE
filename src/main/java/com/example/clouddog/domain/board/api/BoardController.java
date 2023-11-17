package com.example.clouddog.domain.board.api;

import com.example.clouddog.domain.board.api.dto.BoardDto;
import com.example.clouddog.domain.board.api.dto.BoardReqDto;
import com.example.clouddog.domain.board.api.dto.BoardResDto;
import com.example.clouddog.domain.board.application.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class BoardController {

    private final BoardService boardService;
    public BoardController(BoardService boardService){
        this.boardService=boardService;
    }



    //페이징x
    //게시글 목록 불러오기 _ tag가 0이면 전체, 12345면 해당 목록
    @GetMapping("/boards/{bdTag}")
    public ResponseEntity<List<BoardDto>> board(@PathVariable Integer bdTag){
        if (bdTag==0)
            return new ResponseEntity<>(boardService.findAll(), HttpStatus.OK);
        else
            return new ResponseEntity<>(boardService.findAllByTag(bdTag), HttpStatus.OK);
    }

    //게시글 작성
    @PostMapping("/board")
    public ResponseEntity<String> addBoard(@RequestBody BoardReqDto board){
        boardService.boardSave(board);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    //게시글 상세 불러오기
    @GetMapping("/board/{bdId}")
    public ResponseEntity<BoardResDto> findBoard(@PathVariable Long bdId){
        BoardResDto boardDto = boardService.findById(bdId);
        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    //게시글 수정
    @PatchMapping("/board/{bdId}")
    public ResponseEntity<String> updateBoard(@PathVariable Long bdId, @RequestBody BoardReqDto boardDto){
        boardService.boardUpdate(bdId, boardDto);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    //게시글 삭제
    @DeleteMapping("/board/{bdId}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long bdId){
        boardService.boardDelete(bdId);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

}
