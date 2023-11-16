package com.example.clouddog.domain.comment.application;

import com.example.clouddog.domain.board.domain.repository.BoardRepository;
import com.example.clouddog.domain.board.exception.NotFoundBoardException;
import com.example.clouddog.domain.comment.api.CommentDto;
import com.example.clouddog.domain.comment.domain.Comment;
import com.example.clouddog.domain.comment.domain.repository.CommentRepository;
import com.example.clouddog.domain.comment.exception.NotFoundCommentException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public CommentService(CommentRepository commentRepository, BoardRepository boardRepository){

        this.commentRepository=commentRepository;
        this.boardRepository=boardRepository;
    }


    public Long commentSave(Long boardId, CommentDto commentDto){
        Comment comment= new Comment(
                commentDto.getCmContent(),
                commentDto.getCmLikes(),
                commentDto.getPreviousCommentId()
        );


        comment.setBoard(boardRepository.findById(boardId).orElseThrow(NotFoundBoardException::new));
        commentRepository.save(comment);

        return comment.getCommentId();
    }
    public CommentDto findById(Long cmId) {
        Comment comment = commentRepository.findById(cmId).orElseThrow(NotFoundCommentException::new);
        CommentDto commentDto = new CommentDto(
                comment.getCommentContent(),
                comment.getCommentLikes(),
                comment.getCommentTime(),
                comment.getPreviousCmId()
        );
        return commentDto;
    }

    public void addCmLikes(Long cmId){
        Comment comment = commentRepository.findById(cmId).orElseThrow(NotFoundCommentException::new);
        comment.addLikes();
    }
    public void subCmLikes(Long cmId){
        Comment comment = commentRepository.findById(cmId).orElseThrow(NotFoundCommentException::new);
        comment.subLikes();
    }
    public List<CommentDto> findAll(Long bdId){
        List<CommentDto> returnList = new ArrayList<>();
        for (Comment comment: commentRepository.findAll()){
            if (comment.getBoard().getBoardId()==bdId){
                CommentDto commentDto = new CommentDto(
                        comment.getCommentContent(),
                        comment.getCommentLikes(),
                        comment.getCommentTime(),
                        comment.getPreviousCmId()
                );
                returnList.add(commentDto);
            }
        }
        return returnList;
    }
    public void commentUpdate(Long cmId, CommentDto updateCm){
        Comment comment = commentRepository.findById(cmId).orElseThrow(NotFoundCommentException::new);
        comment.update(
                updateCm.getPreviousCommentId(),
                updateCm.getCmContent(),
                updateCm.getCmLikes()
                );
    }
    public void commentDelete(Long cmId){
        commentRepository.deleteById(cmId);
    }
}
