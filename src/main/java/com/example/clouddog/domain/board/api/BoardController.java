package com.example.clouddog.domain.board.api;

import com.example.clouddog.domain.board.api.dto.BoardDto;
import com.example.clouddog.domain.board.api.dto.BoardReqDto;
import com.example.clouddog.domain.board.api.dto.BoardResDto;
import com.example.clouddog.domain.board.application.BoardService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    //페이징x
    //게시글 목록 불러오기 _ tag가 0이면 전체, 12345면 해당 목록
    @GetMapping("/{memberId}/boards/{bdTag}")
    public ResponseEntity<List<BoardDto>> board(@PathVariable(name = "memberId") Long memberId,
                                                @PathVariable Integer bdTag) {
        if (bdTag == 0) {
            return new ResponseEntity<>(boardService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(boardService.findAllByTag(bdTag), HttpStatus.OK);
        }
    }
    //페이징 불러오기
    @GetMapping("/{memberId}/boards")
    public ResponseEntity<Page<BoardDto>> myScrapList(@PathVariable("memberId") Long memberId,
                                                      @RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "size", defaultValue = "8") int size) {
        return new ResponseEntity<>(boardService.findAllPage(memberId, page, size), HttpStatus.OK);
    }

    //게시글 작성
    @PostMapping("/{memberId}/board")
    public ResponseEntity<String> addBoard(@PathVariable(name = "memberId") Long memberId,
                                           @RequestBody BoardReqDto board) {
        boardService.boardSave(memberId, board);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    //게시글 상세 불러오기
    @GetMapping("/{memberId}/board/{bdId}")
    public ResponseEntity<BoardResDto> findBoard(@PathVariable(name = "memberId") Long memberId,
                                                 @PathVariable Long bdId) {
        BoardResDto boardDto = boardService.findById(bdId);
        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    //게시글 수정
    @PatchMapping("/{memberId}/board/{bdId}")
    public ResponseEntity<String> updateBoard(@PathVariable(name = "memberId") Long memberId, @PathVariable Long bdId,
                                              @RequestBody BoardReqDto boardDto) {
        boardService.boardUpdate(bdId, boardDto);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    //게시글 삭제
    @DeleteMapping("/{memberId}/board/{bdId}")
    public ResponseEntity<String> deleteBoard(@PathVariable(name = "memberId") Long memberId, @PathVariable Long bdId) {
        boardService.boardDelete(bdId);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

}
