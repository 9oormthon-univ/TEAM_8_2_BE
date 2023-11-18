package com.example.clouddog.board.application;

import com.example.clouddog.board.api.dto.BoardDto;
import com.example.clouddog.board.api.dto.BoardReqDto;
import com.example.clouddog.board.api.dto.BoardResDto;
import com.example.clouddog.board.domain.Board;
import com.example.clouddog.board.domain.repository.BoardRepository;
import com.example.clouddog.board.exception.NotFoundBoardException;
import com.example.clouddog.board.exception.NotFoundMemberException;
import com.example.clouddog.comment.api.dto.CommentResDto;
import com.example.clouddog.comment.domain.Comment;
import com.example.clouddog.comment.domain.repository.CommentRepository;
import com.example.clouddog.image.domain.Image;
import com.example.clouddog.image.domain.repository.ImageRepository;
import com.example.clouddog.member.domain.Member;
import com.example.clouddog.member.domain.repository.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;
    private final CommentRepository commentRepository;

    public BoardService(BoardRepository boardRepository, MemberRepository memberRepository,
                        ImageRepository imageRepository, CommentRepository commentRepository) {
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
        this.imageRepository = imageRepository;
        this.commentRepository = commentRepository;
    }

    // 게시글 저장
    @Transactional
    public void boardSave(Long memberId, BoardReqDto boardDto) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Image image = imageRepository.findById(boardDto.getImageId()).orElseThrow(NotFoundBoardException::new);

        Board board = new Board(
                boardDto.getBdTitle(),
                boardDto.getBdPlace(),
                boardDto.getBdTag(),
                boardDto.getBdContent(),
                boardDto.getBdTime(),
                image
        );

        member.addBoards(board);
        boardRepository.save(board);
    }

    // 게시글 상세보기_1개 자세히 불러오기 -> 여기서 member.boards 프록시 문제
    public BoardResDto findById(Long memberId, Long boardId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Board board = boardRepository.findById(boardId).orElseThrow(NotFoundBoardException::new);

        List<CommentResDto> comments = new ArrayList<>();
        for (Comment comment : member.getComments()) {
            comments.add(new CommentResDto(
                    comment.getCommentId(),
                    comment.getMember().getMemberId(),
                    comment.getBoard().getBoardId(),
                    comment.getCommentContent(),
                    comment.getCommentLikes(),
                    comment.getCommentTime()));
        }

        BoardResDto boardDto = new BoardResDto(
                board.getBoardId(),
                board.getBoardTitle(),
                board.getBoardPlace(),
                board.getBoardContent(),
                board.getBoardTag(),
                board.getBoardTime(),
                board.getImage().getImageUrl(),
                comments
        );

        return boardDto;
    }

    public Page<BoardDto> findAllPage(Long memberId, int page, int size) {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundMemberException::new);

        Page<Board> boardListPage;
//        boardListPage = boardRepository.findByMember(member,
//                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "boardId")));

        return null;
//        return boardListPage.map(m -> new BoardDto(
//                m.getBoardId()
//                m.getMember().getMemberId(),
//                m.getBoardTitle(),
//                m.getBoardTag()
//        ));
    }

    public Page<BoardDto> findByTagPage(Long memberId, int tag, int page, int size) {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundMemberException::new);

        Page<Board> boardListPage;
//        boardListPage = boardRepository.findByTag(member, tag,
//                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "boardId")));

        return null;
//        return boardListPage.map(m -> new BoardDto(
//                m.getBoardId(),
//                m.getMember().getMemberId(),
//                m.getBoardTitle(),
//                m.getBoardTag()
//        ));
    }

    // 게시글 수정
    @Transactional
    public void boardUpdate(Long bdId, BoardReqDto boardDto) {
        boardRepository.findById(bdId).orElseThrow(NotFoundBoardException::new).update(boardDto);
    }

    //게시글 삭제
    @Transactional
    public void boardDelete(Long bdId) {
        boardRepository.deleteById(bdId);
    }
}
