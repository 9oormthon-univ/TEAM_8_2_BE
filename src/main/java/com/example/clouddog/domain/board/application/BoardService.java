package com.example.clouddog.domain.board.application;

import com.example.clouddog.domain.board.api.dto.BoardDto;
import com.example.clouddog.domain.board.api.dto.BoardReqDto;
import com.example.clouddog.domain.board.api.dto.BoardResDto;
import com.example.clouddog.domain.board.domain.Board;
import com.example.clouddog.domain.board.domain.repository.BoardRepository;
import com.example.clouddog.domain.board.exception.NotFoundBoardException;
import com.example.clouddog.domain.board.exception.NotFoundMemberException;
import com.example.clouddog.image.domain.Image;
import com.example.clouddog.image.domain.repository.ImageRepository;
import com.example.clouddog.member.domain.Member;
import com.example.clouddog.member.domain.repository.MemberRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;
    //private final CommentRepository commentRepository;

    public BoardService(BoardRepository boardRepository, MemberRepository memberRepository,
                        ImageRepository imageRepository) {
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
        this.imageRepository = imageRepository;
    }

    // 게시글 저장
    public void boardSave(Long memberId, BoardReqDto boardDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundMemberException::new);
        Image image = imageRepository.findById(boardDto.getImageId()).orElseThrow(); // 이미지 id 받아서 image 찾기

        Board board = new Board(
                member,
                boardDto.getBdTitle(),
                boardDto.getBdPlace(),
                boardDto.getBdTag(),
                boardDto.getBdContent(),
                boardDto.getBdTime(),
                image
        );

        boardRepository.save(board);
    }

    // 게시글 상세보기_1개 자세히 불러오기
    public BoardResDto findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(NotFoundBoardException::new);

        BoardResDto boardDto = new BoardResDto(
                board.getBoardId(),
                board.getBoardTitle(),
                board.getBoardPlace(),
                board.getBoardContent(),
                board.getBoardTag(),
                board.getBoardTime()
                //board.getComments()
        );
        return boardDto;
    }

    //나중에 페이징. 지금은 확인용
    // 게시글 목록 불러오기(제목/아이디/태그만)
    public List<BoardDto> findAll() {
        List<BoardDto> returnList = new ArrayList<>();
        for (Board board : boardRepository.findAll()) {
            BoardDto boardDto = new BoardDto(
                    board.getBoardId(),
                    board.getBoardTitle(),
                    board.getBoardTag()
            );
            returnList.add(boardDto);
        }
        return returnList;
    }

    // 같은 태그의 게시글 불러오기
    public List<BoardDto> findAllByTag(Integer bdTag) {
        List<BoardDto> returnList = new ArrayList<>();
        for (Board board : boardRepository.findByBoardTag(bdTag)) {
            BoardDto boardDto = new BoardDto(
                    board.getBoardId(),
                    board.getBoardTitle(),
                    board.getBoardTag()
            );
            returnList.add(boardDto);
        }
        return returnList;
    }

    // 게시글 수정
    public void boardUpdate(Long bdId, BoardReqDto boardDto) {
        boardRepository.findById(bdId).orElseThrow(NotFoundBoardException::new).update(
                boardDto.getBdTitle(),
                boardDto.getBdPlace(),
                boardDto.getBdTag(),
                boardDto.getBdContent(),
                boardDto.getBdTime()
        );
    }

    //게시글 삭제
    public void boardDelete(Long bdId) {
        boardRepository.deleteById(bdId);
    }
}
