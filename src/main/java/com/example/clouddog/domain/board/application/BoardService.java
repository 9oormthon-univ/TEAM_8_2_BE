package com.example.clouddog.domain.board.application;

import com.example.clouddog.domain.board.api.BoardDto;
import com.example.clouddog.domain.board.domain.Board;
import com.example.clouddog.domain.board.domain.repository.BoardRepository;
import com.example.clouddog.domain.board.exception.NotFoundBoardException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository){
        this.boardRepository=boardRepository;
    }

    public Long boardSave(BoardDto boardDto){
        Board board = new Board(
                boardDto.getBdTitle(),
                boardDto.getBdPlace(),
                boardDto.getBdTag(),
                boardDto.getBdContent());
        boardRepository.save(board);
        return board.getBoardId();
    }
    public BoardDto findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(NotFoundBoardException::new);
        BoardDto boardDto = new BoardDto(
                board.getBoardTitle(),
                board.getBoardPlace(),
                board.getBoardTag(),
                board.getBoardContent()
        );
        return boardDto;
    }

    //나중에 페이징. 지금은 확인용
    public List<BoardDto> findAll(){
        List<BoardDto> returnList = new ArrayList<>();
        for (Board board: boardRepository.findAll()) {
            BoardDto boardDto = new BoardDto(
                    board.getBoardTitle(),
                    board.getBoardPlace(),
                    board.getBoardTag(),
                    board.getBoardContent()
            );
            returnList.add(boardDto);
        }
        return returnList;
    }

    //이 방식 혹시 개선해야하거나 버그 위험 있나
    public List<BoardDto> findAllByTag(Integer bdTag){
        List<BoardDto> returnList = new ArrayList<>();
        for (Board board: boardRepository.findAll()) {
            if (board.getBoardTag().equals(bdTag)){
                BoardDto boardDto = new BoardDto(
                        board.getBoardTitle(),
                        board.getBoardPlace(),
                        board.getBoardTag(),
                        board.getBoardContent()
                );
                returnList.add(boardDto);
            }
        }
        return returnList;
    }
    public void boardUpdate(Long bdId, BoardDto boardDto){
        Board findBd = boardRepository.findById(bdId).orElseThrow(NotFoundBoardException::new);
        findBd.update(
                boardDto.getBdTitle(),
                boardDto.getBdPlace(),
                boardDto.getBdTag(),
                boardDto.getBdContent());
    }
    public void boardDelete(Long bdId){
        boardRepository.deleteById(bdId);
    }
}
