package com.example.clouddog.comment.application;

import com.example.clouddog.board.domain.Board;
import com.example.clouddog.board.domain.repository.BoardRepository;
import com.example.clouddog.board.exception.NotFoundBoardException;
import com.example.clouddog.board.exception.NotFoundMemberException;
import com.example.clouddog.comment.api.dto.CommentReqDto;
import com.example.clouddog.comment.domain.repository.CommentRepository;
import com.example.clouddog.comment.exception.NotFoundCommentException;
import com.example.clouddog.member.domain.Member;
import com.example.clouddog.member.domain.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public CommentService(CommentRepository commentRepository, BoardRepository boardRepository,
                          MemberRepository memberRepository) {
        this.commentRepository = commentRepository;
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
    }

    // 댓글 저장
    public void commentSave(Long boardId, CommentReqDto commentDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(NotFoundBoardException::new);
        Member member = memberRepository.findById(commentDto.getMemberId()).orElseThrow(NotFoundMemberException::new);
//
//        Comment comment = new Comment(
//                member,
//                board,
//                commentDto.getCmContent(),
//                commentDto.getPreviousCommentId()
//        );

        member.addComments(board, commentDto.getCmContent(), commentDto.getPreviousCommentId());
        memberRepository.save(member);
//        commentRepository.save(comment);
        //실패
        //boardRepository.findById(boardId).orElseThrow(NotFoundBoardException::new).getComments().add(comment);
    }

    //댓글 좋아요 추가
    public void addCmLikes(Long cmId) {
        commentRepository.findById(cmId).orElseThrow(NotFoundCommentException::new).addLikes();
    }

    //댓글 좋아요 취소
    public void subCmLikes(Long cmId) {
        commentRepository.findById(cmId).orElseThrow(NotFoundCommentException::new).subLikes();
    }

    //댓글 수정
    public void commentUpdate(Long cmId, CommentReqDto updateCm) {
        commentRepository.findById(cmId).orElseThrow(NotFoundCommentException::new).update(
                updateCm.getCmContent()
        );
    }

    //댓글 삭제
    public void commentDelete(Long cmId) {
        //실패
        //Comment comment = commentRepository.findById(cmId).orElseThrow(NotFoundCommentException::new);
        //Board board = boardRepository.findById(comment.getBoardId().getBoardId()).orElseThrow(NotFoundBoardException::new);
        //board.getComments().remove(comment);
        commentRepository.deleteById(cmId);
    }
}
