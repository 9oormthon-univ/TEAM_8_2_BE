package com.example.clouddog.domain.board.api;

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


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/boards/{bdTag}")
    public ResponseEntity<List<BoardDto>> board(@PathVariable Integer bdTag){
        if (bdTag==0)
            return new ResponseEntity<>(boardService.findAll(), HttpStatus.OK);
        else
            return new ResponseEntity<>(boardService.findAllByTag(bdTag), HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/board")
    public ResponseEntity<Long> addBoard(@RequestBody BoardDto board){
        Long bdId = boardService.boardSave(board);
        return new ResponseEntity<>(bdId, HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/board/{bdId}")
    public ResponseEntity<BoardDto> findBoard(@PathVariable Long bdId){
        BoardDto boardDto = boardService.findById(bdId);
        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/board/{bdId}")
    public ResponseEntity<String> updateBoard(@PathVariable Long bdId, @RequestBody BoardDto bdData){
        boardService.boardUpdate(bdId, bdData);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/board/{bdId}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long bdId){
        boardService.boardDelete(bdId);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

}
