package com.example.clouddog.domain.comment.application;

import com.example.clouddog.domain.board.domain.Board;
import com.example.clouddog.domain.board.domain.repository.BoardRepository;
import com.example.clouddog.domain.board.exception.NotFoundBoardException;
import com.example.clouddog.domain.comment.api.dto.CommentReqDto;
import com.example.clouddog.domain.comment.api.dto.CommentResDto;
import com.example.clouddog.domain.comment.domain.Comment;
import com.example.clouddog.domain.comment.domain.repository.CommentRepository;
import com.example.clouddog.domain.comment.exception.NotFoundCommentException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public CommentService(CommentRepository commentRepository, BoardRepository boardRepository){

        this.commentRepository=commentRepository;
        this.boardRepository=boardRepository;
    }

    // 댓글 저장
    public void commentSave(Long boardId, CommentReqDto commentDto){
        Board board = boardRepository.findById(boardId).orElseThrow(NotFoundBoardException::new);
        Comment comment= new Comment(
                commentDto.getCmContent(),
                commentDto.getPreviousCommentId(),
                board
        );
        commentRepository.save(comment);
        //실패
        //boardRepository.findById(boardId).orElseThrow(NotFoundBoardException::new).getComments().add(comment);
    }
    //댓글 불러오기
    public CommentResDto findById(Long cmId) {
        Comment comment = commentRepository.findById(cmId).orElseThrow(NotFoundCommentException::new);
        CommentResDto commentDto = new CommentResDto(
                comment.getPreviousCmId(),
                comment.getCommentContent(),
                comment.getCommentLikes(),
                comment.getCommentTime()
        );
        return commentDto;
    }

    //댓글 좋아요 추가
    public void addCmLikes(Long cmId){
        commentRepository.findById(cmId).orElseThrow(NotFoundCommentException::new).addLikes();
    }
    //댓글 좋아요 취소
    public void subCmLikes(Long cmId){
        commentRepository.findById(cmId).orElseThrow(NotFoundCommentException::new).subLikes();
    }
    //댓글 목록 불러오기(보드에서 불러오는걸로 수정)=>실패
    public List<CommentResDto> findAll(Long bdId){
        Board board = boardRepository.findById(bdId).orElseThrow(NotFoundBoardException::new);
        List<CommentResDto> returnList = new ArrayList<>();
        for (Comment comment: commentRepository.findByBoard(board)){
            CommentResDto commentDto = new CommentResDto(
                    comment.getCommentId(),
                    comment.getCommentContent(),
                    comment.getCommentLikes(),
                    comment.getCommentTime()
            );
            returnList.add(commentDto);
        }
        return returnList;
    }
    //댓글 수정
    public void commentUpdate(Long cmId, CommentReqDto updateCm){
        commentRepository.findById(cmId).orElseThrow(NotFoundCommentException::new).update(
                updateCm.getCmContent()
                );
    }
    //댓글 삭제
    public void commentDelete(Long cmId){
        //실패
        //Comment comment = commentRepository.findById(cmId).orElseThrow(NotFoundCommentException::new);
        //Board board = boardRepository.findById(comment.getBoardId().getBoardId()).orElseThrow(NotFoundBoardException::new);
        //board.getComments().remove(comment);
        commentRepository.deleteById(cmId);
    }
}
