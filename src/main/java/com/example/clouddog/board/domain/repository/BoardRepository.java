package com.example.clouddog.board.domain.repository;

import com.example.clouddog.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
//    List<Board> findByBoardTag(int tagNumber);
//
//    Optional<Board> findByMember(Member member);

//    @Query("select b " +
//            "from Board b " +
//            "where b.member = :member "
//    )
//    Page<Board> findByMember(@Param("member") Member member, PageRequest pageRequest);

//    @Query("select b " +
//            "from Board b " +
//            "where b.member = :member and b.boardTag = :boardTag"
//    )
//    Page<Board> findByTag(@Param("member") Member member, @Param("boardTag") int boardTag, PageRequest pageRequest);
}
